package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.models.Likeable;

import java.util.UUID;

public interface LikeService<T extends Likeable> {
    void addLike(UUID entityId, UUID userId);
    boolean supports(String type);
}
