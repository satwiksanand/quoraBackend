package com.github.satwiksanand.quoraBackend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequest {
    private String userName;
    private String userEmail;
    private String profileImage;
    private String userDescription;
}
