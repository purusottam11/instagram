package com.purusottam.instagram.controller;

import com.purusottam.instagram.beans.CommentBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.service.CommentService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<CommentBean> addComment(@RequestBody CommentBean commentBean) {
        try {
            return new ResponseEntity<>(commentService.addComment(commentBean), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/editComment/{commentId}")
    public ResponseEntity<CommentBean> editComment(@PathVariable String commentId, @RequestBody CommentBean commentBean) {
        try {
            return new ResponseEntity<>(commentService.editComment(commentId, commentBean), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        try {
            return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/getCommentsByActivityId/{activityId}")
    public ResponseEntity<List<CommentBean>> getCommentsByActivityId(@PathVariable String activityId) {
        try {
            return new ResponseEntity<>(commentService.getCommentsByActivityId(activityId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

}
