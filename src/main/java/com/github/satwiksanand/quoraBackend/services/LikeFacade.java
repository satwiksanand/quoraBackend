package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.models.Likeable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeFacade {
    private final List<LikeService<? extends Likeable>> likeServices;


    public LikeFacade(List<LikeService<? extends Likeable>> likeServices) {
        this.likeServices = likeServices;
    }

    public void addLike(String type, UUID entityId, UUID userId){
        LikeService<?> service = likeServices.stream()
                .filter(s -> s.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown type: " + type));
        service.addLike(entityId, userId);
    }
}
