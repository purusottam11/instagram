package com.purusottam.instagram.service.impl;

import com.purusottam.instagram.beans.LikeBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.exception.ErrorCode;
import com.purusottam.instagram.model.Like;
import com.purusottam.instagram.model.LikeSummery;
import com.purusottam.instagram.model.Post;
import com.purusottam.instagram.model.Profile;
import com.purusottam.instagram.repository.LikeRepository;
import com.purusottam.instagram.repository.LikeSummeryRepository;
import com.purusottam.instagram.repository.PostRepository;
import com.purusottam.instagram.repository.ProfileRepository;
import com.purusottam.instagram.service.LikeService;
import com.purusottam.instagram.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeSummeryRepository likeSummeryRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public LikeServiceImpl() {
    }

    @Override
    public LikeBean addLike(LikeBean likeBean) {
        Post post = postRepository.findById(likeBean.getActivityId()).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        Like like = new Like();
        CopyUtils.copySafe(likeBean, like);
        likeRepository.findByProfileIdAndActivityId(likeBean.getProfileId(), likeBean.getActivityId()).ifPresent(
                i -> {
                    throw new BusinessException(ErrorCode.LIKE_iS_EXIST);
                }
        );
        like = likeRepository.save(like);
        LikeSummery likeSummery = likeSummeryRepository.findByActivityId(like.getActivityId()).orElse(new LikeSummery());
        if (likeSummery.getActivityId() == null) {
            likeSummery.setActivityId(like.getActivityId());
            likeSummery.setCount(1);
        } else {
            likeSummery.setCount(likeSummery.getCount() + 1);
        }
        likeSummeryRepository.save(likeSummery);
        return likeBean;
    }

    @Override
    @Transactional
    public String deleteLike(String activityId, String profileId) {
        Like like = likeRepository.findByProfileIdAndActivityId(profileId, activityId).orElseThrow(
                () -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));
        likeRepository.deleteById(like.getLikeId());
        LikeSummery likeSummery = likeSummeryRepository.findByActivityId(activityId).get();
        likeSummery.setCount(likeSummery.getCount() - 1);
        likeSummeryRepository.save(likeSummery);
        return "Like deleted successfully !!";
    }

    @Override
    public List<Profile> getProfilesByActivityId(String activityId) {
        List<Like> likes = likeRepository.findByActivityId(activityId).orElseThrow(
                () -> new BusinessException("No Profile found"));
        List<Profile> profiles = new ArrayList<>();
        for (Like like : likes) {
            Profile profile = profileRepository.findById(like.getProfileId()).get();
            profiles.add(profile);
        }
        return profiles;
    }

    @Override
    public Integer getLikeCount(String activityId) {
        LikeSummery likeSummery = likeSummeryRepository.findByActivityId(activityId).orElse(new LikeSummery());
        if (likeSummery.getCount() != null) {
            return likeSummery.getCount();
        }
        return 0;
    }
}
