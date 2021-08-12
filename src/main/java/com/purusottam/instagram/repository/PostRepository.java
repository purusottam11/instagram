package com.purusottam.instagram.repository;

import com.purusottam.instagram.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
