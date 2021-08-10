package com.purusottam.instagram.beans;

import com.purusottam.instagram.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileBean {
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

    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private String imageUrl;
    private String description;
    private Date dob;
    private Profile.ProfileType profileType;
    private Long numberOfPost;
    private Long numberOfFollowers;
    private Long numberOfFollowing;
    private Boolean active;
}
