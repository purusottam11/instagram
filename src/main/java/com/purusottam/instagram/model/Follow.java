package com.purusottam.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Follow {
    @Id
    private String followId;
    // who is the other profile like Me @Applepie_404
    private String followerId;
    // Whom get followed like @MS_Dhoni
    private String followingId;
}
