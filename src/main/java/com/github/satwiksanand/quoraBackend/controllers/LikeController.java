package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.LikeRequest;
import com.github.satwiksanand.quoraBackend.services.LikeFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/{type}/{id}/likes")
public class LikeController {
    private final LikeFacade likeFacade;

    public LikeController(LikeFacade likeFacade) {
        this.likeFacade = likeFacade;
    }

    @PostMapping
    public ResponseEntity<String> like(
            @PathVariable String type,
            @PathVariable UUID id,
            @RequestBody LikeRequest request) {
        likeFacade.addLike(type, id, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Liked successfully");
    }
}

