package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Data
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
}
