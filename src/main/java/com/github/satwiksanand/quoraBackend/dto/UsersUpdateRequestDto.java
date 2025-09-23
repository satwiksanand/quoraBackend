package com.github.satwiksanand.quoraBackend.dto;

import lombok.*;

//i am avoiding the builder notation, hope i don't need to create it in the future.
@Getter
@Setter
public class UsersUpdateRequestDto {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userDescription;
    private String profileImage;
}
