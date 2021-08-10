package com.purusottam.instagram.service;

import com.purusottam.instagram.beans.ProfileBean;
import com.purusottam.instagram.beans.ProfileBeanForSignUp;

public interface ProfileService {
    ProfileBeanForSignUp signUpProfile(ProfileBeanForSignUp profileBeanForSignUp);
    ProfileBean editProfile(ProfileBean profileBean,String profileId);
    String deactivateProfile(String profileId);
    ProfileBean getProfile(String profileId);
    Boolean isUserNameExist(String userName);
}
