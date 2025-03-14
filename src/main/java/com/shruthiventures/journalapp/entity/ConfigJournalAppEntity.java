package com.shruthiventures.journalapp.entity;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("config_journal_app")
@Data
public class ConfigJournalAppEntity {

    @Id
    String id;

    String key;
    String value;
}
