package com.example.luggagekeeper.models;
package com.example.luggagekeeper.models.enumerations;
import com.example.luggagekeeper.models.enumerations.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String Password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String s, String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        Password = password;
    }
}
