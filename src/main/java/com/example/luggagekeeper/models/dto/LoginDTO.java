package com.example.luggagekeeper.models.dto;
package com.example.luggagekeeper.models.enumerations;

import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;
import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    String username username;
    private String password;
    private Role role;

    public static LoginDTO of(User user) {
        LoginDTO login = new LoginDTO();
        login.username = user.getUsername();
        login.role = user.getRole();
        return login;
    }
}