package com.purusottam.instagram.service;

import com.purusottam.instagram.beans.CommentBean;

import java.util.List;

public interface CommentService {

    CommentBean addComment(CommentBean commentBean);

    CommentBean editComment(String commentId, CommentBean commentBean);

    String deleteComment(String commentId);

    List<CommentBean> getCommentsByActivityId(String activityId);

}
