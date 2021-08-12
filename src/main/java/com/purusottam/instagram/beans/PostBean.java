package com.purusottam.instagram.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostBean {

    private String description;
    private List<String> imageUrl;
    private List<String> tags;
    private Instant timestamp;
    // This is the foreign key to the Profile Document as (Many post belongs to one profile)
    private String profileId;
    // The profile who are tagged in the post
    private List<String> taggedProfile;
    // Optional
    private String location;

}
