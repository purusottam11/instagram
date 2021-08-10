package com.purusottam.instagram.service.impl;

import com.purusottam.instagram.beans.ProfileBean;
import com.purusottam.instagram.beans.ProfileBeanForSignUp;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.exception.ErrorCode;
import com.purusottam.instagram.model.Profile;
import com.purusottam.instagram.repository.ProfileRepository;
import com.purusottam.instagram.service.ProfileService;
import com.purusottam.instagram.utils.CopyUtils;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public ProfileBeanForSignUp signUpProfile(ProfileBeanForSignUp profileBeanForSignUp) {
        profileRepository.findByUserName(profileBeanForSignUp.getUserName()).ifPresent(
                i -> {
                    throw new BusinessException(ErrorCode.PROFILE_IS_EXIST);
                }
        );
        profileRepository.findByEmailId(profileBeanForSignUp.getEmailId()).ifPresent(
                i -> {
                    throw new BusinessException(ErrorCode.EMAIL_ID_EXISTS);
                }
        );
        Profile profile = new Profile();
        CopyUtils.copySafe(profileBeanForSignUp, profile);
        profile = profileRepository.save(profile);
        CopyUtils.copySafe(profile, profileBeanForSignUp);
        return profileBeanForSignUp;
    }

    @Override
    public ProfileBean editProfile(ProfileBean profileBean, String profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new BusinessException(ErrorCode.PROFILE_NOT_FOUND));
        CopyUtils.copySafe(profileBean, profile);
        profile = profileRepository.save(profile);
        CopyUtils.copySafe(profile, profileBean);
        return profileBean;
    }

    @Override
    public String deactivateProfile(String profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new BusinessException(ErrorCode.PROFILE_NOT_FOUND));
        profile.setActive(false);
        profile = profileRepository.save(profile);
        return "Your Profile deactivated successfully";
    }

    @Override
    public ProfileBean getProfile(String profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new BusinessException(ErrorCode.PROFILE_NOT_FOUND));
        ProfileBean profileBean = new ProfileBean();
        CopyUtils.copySafe(profile, profileBean);
        return profileBean;
    }

    @Override
    public Boolean isUserNameExist(String userName) {
        Boolean isFound = profileRepository.findByUserName(userName).isPresent();
        return isFound;
    }
}
