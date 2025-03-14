package com.shruthiventures.journalapp.scheduler;

import com.shruthiventures.journalapp.cache.AppCache;
import com.shruthiventures.journalapp.entity.JournalEntry;
import com.shruthiventures.journalapp.entity.User;
import com.shruthiventures.journalapp.repository.UserRepoImpl;
import com.shruthiventures.journalapp.service.EmailService;
import com.shruthiventures.journalapp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 17 ? * TUE")
    public void fetchusersAndSendSAMail(){
        List<User> users = userRepo.getUsersForSA();

        for(User user:users){
            List<JournalEntry> journalEntries=user.getJournalEntries();

            List<String> filteredEntries= journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());

            String entry=String.join(" ",filteredEntries);

            String sentiment=sentimentAnalysisService.getSentiment(entry);

            emailService.sendEmail(user.getEmail(),"Sentiment Analysis for this week",sentiment);




        }
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}
