package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.CommentsDto;
import com.github.satwiksanand.quoraBackend.models.Comments;
import com.github.satwiksanand.quoraBackend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody CommentsDto comment) throws Exception{
        return commentService.createComment(comment);
    }
}
