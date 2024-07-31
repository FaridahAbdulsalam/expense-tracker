package com.expenseTracker.expense_tracker.Security;
import com.expenseTracker.expense_tracker.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String firstName,
                         @RequestParam String lastName){
        userService.registerUser(email, password, firstName, lastName);
    }

//    @PostMapping("/login")
//    public boolean login(@RequestParam String email, @RequestParam String password){
//        return authService.authenticate(email, password);
//    }
}

