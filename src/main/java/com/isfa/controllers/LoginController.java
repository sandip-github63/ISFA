package com.isfa.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.models.User;
import com.isfa.payload.request.LoginRequest;
import com.isfa.payload.request.SignupRequest;
import com.isfa.repository.RoleRepository;
import com.isfa.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpSession session) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            // Store user's information in session
            session.setAttribute("user", user.get());

            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "This is the message.");

            Map<String, Object> data = new HashMap<>();
            User userInfo = user.get();
            data.put("user_info", userInfo);
            response.put("data", data);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> showHomePage(@PathVariable("id") Long id) {
        // Query the database to retrieve user information
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String message = "Welcome to the home page, " + user.getUsername() + "!";
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            data.put("user_info", user);
            response.put("data", data);
            response.put("message", message);
            response.put("status", 200);
            return ResponseEntity.ok(response);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", "User not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    

    
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        // Invalidate session
        session.invalidate();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "Logged out successfully");
        return ResponseEntity.ok(response);
    }

    
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Username is already taken.");
            return ResponseEntity.badRequest().body(response);
        }

        // Creating user's account
        User user = new User(
        	    signUpRequest.getUsername(),
        	    signUpRequest.getEmail(),
        	    (signUpRequest.getPassword()), // Hash the password
        	    signUpRequest.getSupervisor(),
        	    signUpRequest.getCompanyId(),
        	    signUpRequest.getDesignation(),
        	    signUpRequest.getMobile(),
        	    signUpRequest.getiRole()
        	);


        // Save user to the database
        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "User registered successfully.");
        response.put("data", user);

        return ResponseEntity.ok(response);
    }

    }

