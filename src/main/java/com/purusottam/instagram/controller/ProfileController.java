package com.purusottam.instagram.controller;

import com.purusottam.instagram.beans.ProfileBean;
import com.purusottam.instagram.beans.ProfileBeanForSignUp;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.service.ProfileService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
    private ProfileService profileService;

    @PostMapping("/signUp")
    public ResponseEntity<ProfileBeanForSignUp> signUp(@RequestBody ProfileBeanForSignUp profileBeanForSignUp) {
        try {
            return new ResponseEntity<>(profileService.signUpProfile(profileBeanForSignUp), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/editProfile/{profileId}")
    public ResponseEntity<ProfileBean> editProfile(@RequestBody ProfileBean profileBean, @PathVariable String profileId) {
        try {
            return new ResponseEntity<>(profileService.editProfile(profileBean, profileId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/deactivateProfile/{profileId}")
    public ResponseEntity<String> deactivateProfile(@PathVariable String profileId) {
        try {
            return new ResponseEntity<>(profileService.deactivateProfile(profileId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/getProfile/{profileId}")
    public ResponseEntity<ProfileBean> getProfile(@PathVariable String profileId) {
        try {
            return new ResponseEntity<>(profileService.getProfile(profileId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/isUserNameExist/{userName}")
    public ResponseEntity<Boolean> isUserNameExist(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(profileService.isUserNameExist(userName), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
