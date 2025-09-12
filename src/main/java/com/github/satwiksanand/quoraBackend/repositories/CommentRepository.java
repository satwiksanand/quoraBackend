package com.github.satwiksanand.quoraBackend.repositories;

import com.github.satwiksanand.quoraBackend.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comments, UUID> {

}
