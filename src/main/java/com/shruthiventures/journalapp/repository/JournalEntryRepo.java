package com.shruthiventures.journalapp.repository;

import com.shruthiventures.journalapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, String> {

}
