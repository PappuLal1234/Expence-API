package com.expense.controller;


import com.expense.entity.User;
import com.expense.service.AuthService;
import com.expense.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody  User user) {
        User existingUser = authService.getAuthenticatedUser(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User registeredUser = authService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody  User loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String token = authService.loginUser(username, password);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(token);
    }
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestBody User updatedUser, @RequestParam String token) {
        User user = authService.getAuthenticatedUser(token);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User existingUser = userService.findUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        User updated = userService.updateUser(existingUser, updatedUser);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestParam String token) {
        User user = authService.getAuthenticatedUser(token);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User existingUser = userService.findUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);
        authService.logoutUser(token);
        return ResponseEntity.noContent().build();
    }
}

