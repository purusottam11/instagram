package com.purusottam.instagram.service.impl;

import com.purusottam.instagram.beans.CommentBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.exception.ErrorCode;
import com.purusottam.instagram.model.Comment;
import com.purusottam.instagram.repository.CommentRepository;
import com.purusottam.instagram.repository.PostRepository;
import com.purusottam.instagram.service.CommentService;
import com.purusottam.instagram.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentBean addComment(CommentBean commentBean) {
        Boolean check = postRepository.findById(commentBean.getActivityId()).isPresent();
        if (!check) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND);
        }
        Comment comment = new Comment();
        CopyUtils.copySafe(commentBean, comment);
        comment = commentRepository.save(comment);
        return commentBean;
    }

    @Override
    public CommentBean editComment(String commentId, CommentBean commentBean) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
        CopyUtils.copySafe(commentBean, comment);
        comment = commentRepository.save(comment);
        CopyUtils.copySafe(comment, commentBean);
        return commentBean;
    }

    @Override
    public String deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
        commentRepository.deleteById(commentId);
        return "Comment deleted successfully !!";
    }

    @Override
    public List<CommentBean> getCommentsByActivityId(String activityId) {
        List<Comment> comments = commentRepository.findByActivityId(activityId).orElseThrow(
                () -> new BusinessException("Comment Not found !!"));
        List<CommentBean> list = CopyUtils.copySafe(comments, CommentBean.class);
        return list;
    }
}
