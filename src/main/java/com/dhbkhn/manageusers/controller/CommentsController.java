package com.dhbkhn.manageusers.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhbkhn.manageusers.model.Comments;
import com.dhbkhn.manageusers.service.Comments.CommentsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comments")
public class CommentsController {

    public final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    // create new comment
    @PostMapping("/createComment")
    public ResponseEntity<String> createComment(
            @RequestBody Comments comment) {
        commentsService.createComment(
                comment.getProduct_id(),
                comment.getUser_id(),
                comment.getContent(),
                comment.getCreated_at(),
                comment.getLikes(),
                comment.getDislikes());
        return ResponseEntity.ok("Comment created successfully");
    }

}
