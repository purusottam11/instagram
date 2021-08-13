package com.purusottam.instagram.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeBean {
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

    // Foreign key Who is giving the like
    private String profileId;
    // Foreign  key as post or comment Id
    private String activityId;
    private ActivityType activityType;
    private Instant timestamp;
}
