package com.expenseTracker.expense_tracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void registerUser(String email, String password, String firstName, String lastName) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            throw new RuntimeException("User already exists");
        }
        User newUser = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .build();
        userRepository.save(newUser);
    }


    public boolean authenticate(String email, String password) {
        Optional<User> userFound = userRepository.findByEmail(email);
       return userFound.isPresent() && passwordEncoder.matches(password, userFound.get().getPassword());
    }
}
