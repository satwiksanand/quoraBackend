package com.github.satwiksanand.quoraBackend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    public String message;
    public int statusCode;
    public String errorMessage;
}
