package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.PostDto;
import com.github.satwiksanand.quoraBackend.exception.EntityNotFoundException;
import com.github.satwiksanand.quoraBackend.models.Posts;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.PostRepository;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private PostDto postMapper(Posts post){
        return PostDto.builder().postContent(post.getPostContent())
                .postViews(post.getPostViews())
                .postTitle(post.getPostTitle())
                .postUpvoteCount(post.getPostUpvoteCount())
                .postDownVote(post.getPostDownVote())
                .build();
    }

    public ResponseEntity<Posts> createPost(PostDto postdto) throws Exception{
        Optional<Users> createdBy = userRepository.findById(postdto.getCreatedBy());

        if(createdBy.isPresent()){
            Posts post = Posts.builder()
                    .postContent(postdto.getPostContent())
                    .createdBy(createdBy.get())
                    .postTitle(postdto.getPostTitle())
                    .postViews(0L)
                    .postDownVote(0L)
                    .postUpvoteCount(0L)
                    .build();
            postRepository.save(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        throw new EntityNotFoundException("Invalid Request!");
    }

    public ResponseEntity<List<PostDto>> searchPosts(String postTitle) throws Exception{
        List<Posts> allPosts = postRepository.findByPostTitleStartingWith(postTitle);
        List<PostDto> result = new ArrayList<>();
        for(Posts post : allPosts){
            result.add(postMapper(post));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
