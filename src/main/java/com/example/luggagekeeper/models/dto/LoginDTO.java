package com.example.luggagekeeper.models.dto;

import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDTO {
    private String email;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

    /*
    public static LoginDTO of(User user) {
        LoginDTO login = new LoginDTO();
        login.username = user.getUsername();
        login.role = user.getRole();
        return login;
    }

     */
