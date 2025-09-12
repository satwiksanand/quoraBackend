package com.github.satwiksanand.quoraBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentsDto {
    private UUID userId;
    private UUID postId;
    private long upvote;
    private String commentContent;
}
