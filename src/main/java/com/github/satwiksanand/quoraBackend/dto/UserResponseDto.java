package com.github.satwiksanand.quoraBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@AllArgsConstructor
public class UserResponseDto {
    public UUID userId;
    public String userName;
}
