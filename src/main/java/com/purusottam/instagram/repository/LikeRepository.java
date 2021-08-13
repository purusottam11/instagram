package com.purusottam.instagram.repository;

import com.purusottam.instagram.model.Like;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends MongoRepository<Like, String> {
    Optional<Like> findByProfileIdAndActivityId(String profileId, String activityId);

    Optional<List<Like>> findByActivityId(@Param("activityId") String activityId);
}
