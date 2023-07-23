package com.expense.service;
import com.expense.entity.User;
import com.expense.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private Map<String, User> authenticatedUsers = new HashMap<>();
    public User registerUser(User user) {
        String token = generateToken();
        user.setToken(token);
        return userRepository.save(user);
    }
    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            String token = generateToken();
            authenticatedUsers.put(token, user);
            return token;
        }
        return null;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
//    public User getAuthenticatedUser(String token) {
//        return authenticatedUsers.get(token);
//    }
public User getAuthenticatedUser(String token) {
    // Find the username associated with the token
    String username = getUsernameByToken(token);
    if (username != null) {
        return userRepository.findByUsername(username);
    }
    return null;
}
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public boolean isTokenValid(String token) {
        return authenticatedUsers.containsKey(token);
    }
//    public void logoutUser(String token) {
//        authenticatedUsers.remove(token);
//    }
public void logoutUser(String token) {
    // Find and remove the username associated with the token
    String username = getUsernameByToken(token);
    if (username != null) {
        authenticatedUsers.remove(username);
    }
}
    private String getUsernameByToken(String token) {
        // Iterate through the map to find the username associated with the token
        for (Map.Entry<String, User> entry : authenticatedUsers.entrySet()) {
            if (entry.getValue().equals(token)) {
                return entry.getKey();
            }
        }
        return null;
    }

}

