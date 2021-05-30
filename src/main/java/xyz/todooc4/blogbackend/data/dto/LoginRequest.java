package xyz.todooc4.blogbackend.data.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
