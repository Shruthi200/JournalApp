package com.shruthiventures.journalapp.cache;

import com.shruthiventures.journalapp.entity.ConfigJournalAppEntity;
import com.shruthiventures.journalapp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    ConfigJournalAppRepo configJournalAppRepo;

    public enum keys{
        WEATHER_API
    }

    public Map<String,String> APP_CACHE=new HashMap<>();

    @PostConstruct
    public void init(){
       List<ConfigJournalAppEntity> all= configJournalAppRepo.findAll();

       for(ConfigJournalAppEntity configJournalAppEntity:all){
           APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
       }
    }
}
