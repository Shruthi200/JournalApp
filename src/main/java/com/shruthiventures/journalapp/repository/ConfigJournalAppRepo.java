package com.shruthiventures.journalapp.repository;

import com.shruthiventures.journalapp.entity.ConfigJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity,String> {

}
