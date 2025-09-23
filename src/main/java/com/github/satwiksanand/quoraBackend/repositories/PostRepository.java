package com.github.satwiksanand.quoraBackend.repositories;

import com.github.satwiksanand.quoraBackend.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Posts, UUID> {
    List<Posts> findByPostTitleStartingWith(String prefix);
}
