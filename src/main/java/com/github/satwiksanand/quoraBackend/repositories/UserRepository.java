package com.github.satwiksanand.quoraBackend.repositories;

import com.github.satwiksanand.quoraBackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    boolean existsByUserEmail(String email);
}
