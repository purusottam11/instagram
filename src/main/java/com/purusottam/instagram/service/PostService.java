package com.purusottam.instagram.service;

import com.purusottam.instagram.beans.PostBean;

public interface PostService {
    String addPost(PostBean postBean);
    PostBean editPost(PostBean postBean,String postId);
    PostBean getPost(String postId);
    String deletePost(String postId);
}
