package com.github.satwiksanand.quoraBackend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequest {
    private String name;
    private String description;
    private Long id;
}
