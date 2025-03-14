package com.shruthiventures.journalapp.service;

import com.shruthiventures.journalapp.entity.JournalEntry;
import com.shruthiventures.journalapp.entity.User;
import com.shruthiventures.journalapp.repository.JournalEntryRepo;
import com.shruthiventures.journalapp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getuserEntryById(String id){
        return  userRepo.findById(id);
    }

    public boolean deleteUserEntryById(String id){
        if(userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        else return false;
    }

    public void deleteUserEntryByUsername(String username){
        userRepo.deleteByUsername(username);
    }



    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }

    public void createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public void updateUser(User newuser, String username){
        User userInDB=userRepo.findByUsername(username);
        System.out.println(userInDB);

        if(userInDB!=null){
            userInDB.setUsername(newuser.getUsername());
            userInDB.setPassword(passwordEncoder.encode(newuser.getPassword()));
        }

        userRepo.save(userInDB);
    }

    public User findByUserName(String username){
        return userRepo.findByUsername(username);
    }

}
