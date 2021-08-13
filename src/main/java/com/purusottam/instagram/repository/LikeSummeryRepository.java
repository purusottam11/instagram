package com.purusottam.instagram.repository;

import com.purusottam.instagram.model.LikeSummery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LikeSummeryRepository extends MongoRepository<LikeSummery, String> {
    Optional<LikeSummery> findByActivityId(String activityId);
}
