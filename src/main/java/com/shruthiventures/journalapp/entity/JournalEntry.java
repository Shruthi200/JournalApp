package com.shruthiventures.journalapp.entity;

import com.shruthiventures.journalapp.enums.Sentiment;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("journal_entries")
@Data
public class JournalEntry {

    @Id
    String id;

    @NonNull
    String title;

    String content;

    LocalDateTime date;

    Sentiment sentiment;
}
