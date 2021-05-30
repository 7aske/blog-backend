package xyz.todooc4.blogbackend.data.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;
    private String username;
    private String displayName;
    private String password;
    private String email;
    private String about;
}
