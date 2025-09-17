package com.github.satwiksanand.quoraBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LikeRequest {
    UUID userId;
}
