package com.shruthiventures.journalapp.controller;

import com.shruthiventures.journalapp.api.response.WeatherResponse;
import com.shruthiventures.journalapp.entity.JournalEntry;
import com.shruthiventures.journalapp.entity.User;
import com.shruthiventures.journalapp.service.UserService;
import com.shruthiventures.journalapp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WeatherService weatherService;




//    @GetMapping("/id/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable String id){
//        Optional<User> j=userService.getuserEntryById(id);
//
//        if(j.isPresent()){
//            return new ResponseEntity<>(j.get(),HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping()
    public ResponseEntity<User> deleteUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserEntryByUsername(authentication.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User newuser){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            userService.updateUser(newuser,username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse=weatherService.getWeather("Bangalore");
        String greeting="Hi "+authentication.getName();

        if(weatherResponse!=null){
            greeting=greeting+".Temperature="+weatherResponse.getCurrent().getTemperature();
        }

        return new ResponseEntity<>(greeting,HttpStatus.OK);
    }


}
