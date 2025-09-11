package com.github.satwiksanand.quoraBackend.dto;

import com.github.satwiksanand.quoraBackend.models.Users;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private String postContent;
    private UUID createdBy;
    private Long postUpvoteCount = 0L;
    private Long postDownVote = 0L;
    private Long postViews = 0L;
}
