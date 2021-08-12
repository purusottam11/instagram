package com.purusottam.instagram.service.impl;

import com.purusottam.instagram.beans.PostBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.exception.ErrorCode;
import com.purusottam.instagram.listener.KafkaListener;
import com.purusottam.instagram.model.Post;
import com.purusottam.instagram.repository.PostRepository;
import com.purusottam.instagram.repository.ProfileRepository;
import com.purusottam.instagram.service.PostService;

import com.purusottam.instagram.utils.CopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class PostServiceImpl implements PostService {

    private final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final String topic = "post_topic";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private KafkaTemplate<String, PostBean> template;

    @Override
    public String addPost(PostBean postBean) {
        ListenableFuture<SendResult<String, PostBean>> listenableFuture = template.send(topic, postBean);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, PostBean>>() {
            @Override
            public void onSuccess(SendResult<String, PostBean> stringPostBeanSendResult) {
                logger.info("Upload successfully : {}", postBean);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error while uploading the post ... ", ex.getMessage());
                throw new BusinessException(ex.getMessage());
            }
        });
        return "Your post uploaded successfully !!";
    }

    @Override
    public PostBean editPost(PostBean postBean, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        CopyUtils.copySafe(postBean, post);
        post = postRepository.save(post);
        return postBean;
    }

    @Override
    public PostBean getPost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        PostBean postBean = new PostBean();
        CopyUtils.copySafe(post, postBean);
        return postBean;
    }

    @Override
    public String deletePost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND));
        postRepository.deleteById(postId);
        return "Post deleted successfully !!";
    }
}
