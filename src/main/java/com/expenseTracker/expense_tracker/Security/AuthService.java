//package com.expenseTracker.expense_tracker.Security;
//
//
//
//import com.expenseTracker.expense_tracker.User.User;
//import com.expenseTracker.expense_tracker.User.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public boolean authenticate(String email, String password) {
//        User user = userRepository.findByEmail(email).orElse(null);
//        if (user != null) {
//            return passwordEncoder.matches(password, user.getPassword());
//        }
//        return false;
//    }
//
//    public void registerUser(String email, String password, String firstName, String lastName) {
//        if (userRepository.findByEmail(email).isPresent()) {
//            throw new RuntimeException("Email already in use");
//        }
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        userRepository.save(user);
//    }
//}
