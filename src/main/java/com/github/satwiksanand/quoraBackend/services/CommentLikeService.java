package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.models.Comments;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.CommentRepository;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentLikeService implements LikeService<Comments>{
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentLikeService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addLike(UUID entityId, UUID userId) {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Comments> comment = commentRepository.findById(entityId);
        if(user.isPresent() && comment.isPresent()){
            comment.get().addLike(user.get());
            commentRepository.save(comment.get());
        }
    }

    @Override
    public boolean supports(String type) {
        return "comment".equalsIgnoreCase(type);
    }
}
