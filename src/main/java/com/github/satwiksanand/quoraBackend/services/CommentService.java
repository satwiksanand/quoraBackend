package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.CommentsDto;
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

import java.util.Optional;


@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<Comments> createComment(CommentsDto comment) throws Exception{
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
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        }
        throw new EntityNotFoundException("user or post does not exist!");
    }
}
