//package com.shruthiventures.journalapp.controller;
//
//import com.shruthiventures.journalapp.entity.JournalEntry;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    Map<Long,JournalEntry> entries=new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAllJournalEntries(){
//        return new ArrayList<>(entries.values());
//    }
//
//    @GetMapping("/id/{id}")
//    public JournalEntry getJournalEntryById(@PathVariable long id){
//        return entries.get(id);
//    }
//
//    @DeleteMapping("/id/{id}")
//    public JournalEntry deleteJournalEntryById(@PathVariable long id){
//        return entries.remove(id);
//    }
//
//
//    @PostMapping
//    public void createJournalEntry(@RequestBody JournalEntry newentry){
//        entries.put(newentry.getId(),newentry);
//    }
//
//    @PutMapping
//    public void updateJournalEntry(@RequestBody JournalEntry newentry){
//        entries.put(newentry.getId(),newentry);
//    }
//
//    @PatchMapping("id/{id}")
//    public void updateByPatch(@PathVariable long id,@RequestBody JournalEntry newentry){
//        JournalEntry entry=entries.get(id);
//
//        if(newentry.getTitle()!=null){
//            entry.setTitle(newentry.getTitle());
//        }
//        if(newentry.getContent()!=null){
//            entry.setContent(newentry.getContent());
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
//    public ResponseEntity<Void> handleOptionsRequest() {
//        return ResponseEntity.ok()
//                .header("Allow", "GET, POST, PUT, DELETE,PATCH,HEAD")
//                .build();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
//    public ResponseEntity<Void> handleHeadRequest(@PathVariable String id) {
//        // Check if the resource exists
//        if (entries.get(id)!=null) {
//            return ResponseEntity.ok().header("Content-Type", "application/json").build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//}
