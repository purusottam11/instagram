package com.purusottam.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Comment {
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
    private String commentId;
    private String comment;
    private String imageUrl;
    // It may be PostId or commentId
    private String ActivityId;
    private ActivityType activityType;
    // From which profile it gets comment
    private String profileId;
    private Instant timestamp;

}
