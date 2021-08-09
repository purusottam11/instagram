package com.purusottam.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profile {

    public enum ProfileType {
        PROFILE("Private"), PUBLIC("Public");
        private String label;
        private ProfileType(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

    @Id
    private String profileId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private String imageUrl;
    private String description;
    private Date dob;
    private ProfileType profileType;
    private Long numberOfPost;
    private Long numberOfFollowers;
    private Long numberOfFollowing;

}
