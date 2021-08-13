package com.purusottam.instagram.service;

import com.purusottam.instagram.beans.LikeBean;
import com.purusottam.instagram.model.Profile;

import java.util.List;

public interface LikeService {

    LikeBean addLike(LikeBean likeBean);

    String deleteLike(String activityId, String profileId);

    List<Profile> getProfilesByActivityId(String activityId);

    Integer getLikeCount(String activityId);
}
