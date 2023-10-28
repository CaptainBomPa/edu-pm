package com.edu.pm.backend.commons.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
