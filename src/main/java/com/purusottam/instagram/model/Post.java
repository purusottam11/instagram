package com.purusottam.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    private String postId;
    private String description;
    private List<String> imageUrl;
    private List<String> tags;
    private Instant timestamp;
    // This is the foreign key to the Profile Document as (Many post belongs to one profile)
    private String profileId;
}
