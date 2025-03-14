package com.shruthiventures.journalapp.repository;

import com.shruthiventures.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
