package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.PostDto;
import com.github.satwiksanand.quoraBackend.models.Posts;
import com.github.satwiksanand.quoraBackend.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Posts> createPost(@RequestBody PostDto post) throws Exception {
        return postService.createPost(post);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam(required = false, defaultValue = "") String title) throws Exception{
        return postService.searchPosts(title);
    }
}
