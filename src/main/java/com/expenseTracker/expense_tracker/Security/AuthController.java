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
    public void register(@RequestBody RegisterRequest registerRequest){
        userService.registerUser(
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        boolean authenticated = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if(authenticated){
            return "Login successful";
        }else {
            return "Invalid email or password";
        }
    }

}

