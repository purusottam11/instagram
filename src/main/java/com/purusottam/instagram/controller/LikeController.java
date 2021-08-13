package com.purusottam.instagram.controller;

import com.purusottam.instagram.beans.LikeBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.model.Profile;
import com.purusottam.instagram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/like")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/addLike")
    public ResponseEntity<LikeBean> addLike(@RequestBody LikeBean likeBean) {
        try {
            return new ResponseEntity<>(likeService.addLike(likeBean), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/deleteLike/{activityId}/{profileId}")
    public ResponseEntity<String> deleteLike(@PathVariable String activityId, @PathVariable String profileId) {
        try {
            return new ResponseEntity<>(likeService.deleteLike(activityId, profileId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/getProfilesByActivityId/{activityId}")
    public ResponseEntity<List<Profile>> getProfilesByActivityId(@PathVariable String activityId) {
        try {
            return new ResponseEntity<>(likeService.getProfilesByActivityId(activityId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/getLikeCount/{activityId}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable String activityId) {
        try {
            return new ResponseEntity<>(likeService.getLikeCount(activityId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
