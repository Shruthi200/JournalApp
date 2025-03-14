package com.shruthiventures.journalapp.service;

import com.shruthiventures.journalapp.entity.JournalEntry;
import com.shruthiventures.journalapp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepo journalEntryRepo;

    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(String id){
        return  journalEntryRepo.findById(id);
    }

    public void deleteJournalEntryById(String id){
            journalEntryRepo.deleteById(id);
    }

    public JournalEntry createJournalEntry(JournalEntry newentry){
        journalEntryRepo.save(newentry);
        return newentry;
    }

    public JournalEntry updateJournalEntry(JournalEntry newentry, String id){
        JournalEntry old=journalEntryRepo.findById(id).orElse(null);

        if(old!=null){
            old.setTitle(newentry.getTitle()!=null ? newentry.getTitle() : old.getTitle());
            old.setContent(newentry.getContent()!=null ? newentry.getContent():old.getContent());
        }

        journalEntryRepo.save(old);
        return old;
    }
}
