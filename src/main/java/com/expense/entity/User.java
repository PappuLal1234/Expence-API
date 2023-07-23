package com.expense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column(unique = true)
    private String password;

    @Transient
    private String token;

    public User(Long id, String username, String token) {
        this.id=id;
        this.username=username;
        this.token=token;
    }

    // Constructors, getters, and setters
}
