package com.purusottam.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
    public enum ActivityType {
        POST("Post "), COMMENT("Comment");
        private String label;
        private ActivityType(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }
    @Id
    private String likeId;
    // Foreign key Who is giving the like
    private String profileId;
    // Foreign  key as post or comment Id
    private String activityId;
    private ActivityType activityType;
    private Instant timestamp;
}
