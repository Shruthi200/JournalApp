package com.shruthiventures.journalapp.controller;

import com.shruthiventures.journalapp.entity.JournalEntry;
import com.shruthiventures.journalapp.entity.User;
import com.shruthiventures.journalapp.repository.UserRepo;
import com.shruthiventures.journalapp.service.JournalEntryService;
import com.shruthiventures.journalapp.service.UserService;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    private Logger logger= LoggerFactory.getLogger(JournalEntryControllerV2.class);


//    @GetMapping
//    public ResponseEntity<List<JournalEntry>> getAllJournalEntries(){
//        List<JournalEntry> all=journalEntryService.getAllJournalEntries();
//
//        if(all!=null ){
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        User user=userRepo.findByUsername(username);

        List<JournalEntry> all=user.getJournalEntries();

        if(all!=null ){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        User user=userService.findByUserName(username);
        Optional<JournalEntry> matchingEntry = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
        if (matchingEntry.isPresent()) {
            return new ResponseEntity<>(matchingEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        User user=userService.findByUserName(username);
        Optional<JournalEntry> matchingEntry = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
        if (matchingEntry.isPresent()) {
            journalEntryService.deleteJournalEntryById(id);
            user.getJournalEntries().removeIf(x->x.getId().equals(id));
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping()
    @Transactional
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry newentry){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            newentry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryService.createJournalEntry(newentry);
            User user=userRepo.findByUsername(username);
            user.getJournalEntries().add(saved);
            userRepo.save(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@RequestBody JournalEntry newentry,@PathVariable String id) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            Optional<JournalEntry> matchingEntry = user.getJournalEntries()
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst();
            if (matchingEntry.isPresent()) {
                journalEntryService.updateJournalEntry(newentry, id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }


}
