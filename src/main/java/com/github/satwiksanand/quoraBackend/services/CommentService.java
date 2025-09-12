package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.CommentResponseDto;
import com.github.satwiksanand.quoraBackend.dto.CommentsDto;
import com.github.satwiksanand.quoraBackend.dto.UserResponseDto;
import com.github.satwiksanand.quoraBackend.exception.EntityNotFoundException;
import com.github.satwiksanand.quoraBackend.models.Comments;
import com.github.satwiksanand.quoraBackend.models.Posts;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.CommentRepository;
import com.github.satwiksanand.quoraBackend.repositories.PostRepository;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    //utility function to convert comment response to comment dto
    private CommentResponseDto convertCommentToDto(Comments comment){
        UserResponseDto user = UserResponseDto.builder()
                .userId(comment.getCommentedBy().getUserId())
                .userName(comment.getCommentedBy().getUserName())
                .build();
        return CommentResponseDto.builder()
                .commentedBy(user)
                .modifiedAt(comment.getModifiedAt())
                .upvote(comment.getUpvote())
                .commentContent(comment.getCommentContent())
                .build();
    }

    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<CommentResponseDto> createComment(CommentsDto comment) throws Exception{
        Optional<Users> user = userRepository.findById(comment.getUserId());
        Optional<Posts> post = postRepository.findById(comment.getPostId());
        if(user.isPresent() && post.isPresent()){
            Comments newComment = Comments.builder()
                    .commentContent(comment.getCommentContent())
                    .post(post.get())
                    .commentedBy(user.get())
                    .upvote(0L)
                    .build();
            commentRepository.save(newComment);
            return new ResponseEntity<>(convertCommentToDto(newComment), HttpStatus.CREATED);
        }
        throw new EntityNotFoundException("user or post does not exist!");
    }

    public ResponseEntity<List<CommentResponseDto>> getCommentByPostId(UUID postId) throws Exception{
        if(postRepository.existsById(postId)){
            List<Comments> allComments = commentRepository.findAllByPost_PostId(postId);
            List<CommentResponseDto> result = allComments.stream()
                    .map(this::convertCommentToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }
        throw new EntityNotFoundException("post not found!");
    }
}
