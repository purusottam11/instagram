package com.purusottam.instagram.repository;

import com.purusottam.instagram.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUserName(String userName);
    Optional<Profile> findByEmailId(String emailId);
}
