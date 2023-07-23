package com.expense.service;

import com.expense.entity.User;
import com.expense.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User existingUser, User updatedUser) {
        // Implement the logic to update user fields and save the updated user in the repository
        // For simplicity, let's assume we only update the username and password
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Additional methods for three months record and total expenditure

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByDateAndTime(LocalDateTime dateTime) {
        // Implement the logic to fetch users based on the given date and time
        // For example, you can use a custom query to filter users based on date and time
        // return userRepository.findByDateAndTime(dateTime);
        return Collections.emptyList(); // Replace this with actual implementation
    }

    public double getTotalExpenditureByMonth(String username, int month) {
        // Implement the logic to calculate the total expenditure for the given month
        // You may need to fetch the user's expenses for the specified month and sum their prices
        // return userRepository.getTotalExpenditureByMonth(username, month);
        return 0.0; // Replace this with actual implementation
    }

}