package com.purusottam.instagram.controller;

import com.purusottam.instagram.beans.PostBean;
import com.purusottam.instagram.exception.BusinessException;
import com.purusottam.instagram.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping("/uploadPost")
    public ResponseEntity<String> uploadPost(@RequestBody PostBean postBean) {
        try {
            return new ResponseEntity<>(postService.addPost(postBean), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getCause().toString());
        }
    }

    @PutMapping("/editPost/{postId}")
    public ResponseEntity<PostBean> editPost(@RequestBody PostBean postBean, @PathVariable String postId) {
        try {
            return new ResponseEntity<>(postService.editPost(postBean, postId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<PostBean> getPost(@PathVariable String postId) {
        try {
            return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        try {
            return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
