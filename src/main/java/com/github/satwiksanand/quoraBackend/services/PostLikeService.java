package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.models.Posts;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.PostRepository;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostLikeService implements LikeService<Posts>{
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    PostLikeService(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void addLike(UUID entityId, UUID userId) {
        Optional<Posts> post = postRepository.findById(entityId);
        Optional<Users> user = userRepository.findById(userId);
        if(post.isPresent() && user.isPresent()){
            post.get().addLike(user.get());
            postRepository.save(post.get());
        }
    }

    @Override
    public boolean supports(String type) {
        return "posts".equalsIgnoreCase(type);
    }
}
