package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String login;

    @NonNull
    @JoinColumn(nullable = false)
    private String password;

    public User(@NonNull String login, @NonNull String password) {
        this.login = login;
        this.password = password;
    }
}
