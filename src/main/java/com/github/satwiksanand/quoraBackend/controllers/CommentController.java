package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.CommentResponseDto;
import com.github.satwiksanand.quoraBackend.dto.CommentsDto;
import com.github.satwiksanand.quoraBackend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentsDto comment) throws Exception{
        return commentService.createComment(comment);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentByPostId(@PathVariable UUID postId) throws Exception{
        return commentService.getCommentByPostId(postId);
    }
}
