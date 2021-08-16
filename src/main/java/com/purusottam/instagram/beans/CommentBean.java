package com.purusottam.instagram.beans;

import com.purusottam.instagram.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBean {

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

    private String comment;
    private String imageUrl;
    // It may be PostId or commentId
    private String activityId;
    private ActivityType activityType;
    // From which profile it gets comment
    private String profileId;
    private Instant timestamp;
}
