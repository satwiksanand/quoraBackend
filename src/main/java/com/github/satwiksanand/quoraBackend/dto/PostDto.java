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
    private String postTitle;
    private String postContent;
    private UUID createdBy;
    private Long postUpvoteCount;
    private Long postDownVote;
    private Long postViews;
}
