package com.github.satwiksanand.quoraBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    public UserResponseDto commentedBy;
    public long upvote;
    public String commentContent;
    public Date modifiedAt;
}
