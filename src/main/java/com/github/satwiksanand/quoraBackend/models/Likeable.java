package com.github.satwiksanand.quoraBackend.models;

import java.util.UUID;

public interface Likeable {
    UUID getId();
    void addLike(Users user);
    int getLikeCount();
}
