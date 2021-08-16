package com.purusottam.instagram.repository;

import com.purusottam.instagram.model.Comment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<List<Comment>> findByActivityId(@Param("activityId") String activityId);

}
