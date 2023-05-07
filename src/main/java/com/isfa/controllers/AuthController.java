/*package com.isfa.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.models.BaseResponseAuth;
import com.isfa.models.ERole;
import com.isfa.models.Role;
import com.isfa.models.User;
import com.isfa.payload.request.LoginRequest;
import com.isfa.payload.request.SignupRequest;
import com.isfa.payload.response.JwtResponse;
import com.isfa.payload.response.MessageResponse;
import com.isfa.repository.RoleRepository;
import com.isfa.repository.UserRepository;
import com.isfa.security.jwt.JwtUtils;
import com.isfa.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    
    

//    return ResponseEntity.ok(new JwtResponse(jwt, 
//                         userDetails.getId(), 
//                         userDetails.getUsername(), 
//                         userDetails.getEmail(), 
//                         roles
//                         ));
    JwtResponse jwtResponse = new JwtResponse(jwt, 
            userDetails.getId(), 
            userDetails.getUsername(), 
            userDetails.getEmail(), 
            roles
            );
return ResponseEntity.ok(new BaseResponseAuth<JwtResponse>("", "200", jwtResponse));

    
  }
  
  
  @GetMapping("/user")
  public ResponseEntity<Map<String, Object>> getUserDetails(@AuthenticationPrincipal UserDetailsImpl userDetails) {
      Map<String, Object> response = new HashMap<>();
      if (userDetails != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//          response.put("name", userDetails.getUsername());
//          response.put("email", userDetails.getEmail());
          //return ResponseEntity.ok(response);
          
          response.put("name", userDetails.getUsername());
          response.put("email", userDetails.getEmail());
          BaseResponseAuth<Map<String, Object>> baseResponse = new BaseResponseAuth<>("", "200", response);
          return ResponseEntity.ok(baseResponse.getData());
      } else {
          // handle unauthorized access
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
  }
  
  
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser() {
    SecurityContextHolder.clearContext();
    //return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
    MessageResponse messageResponse = new MessageResponse("User logged out successfully!");
    return ResponseEntity.ok(new BaseResponseAuth<MessageResponse>("", "200", messageResponse));

  }

  

  @PostMapping("/signup")
  @org.springframework.transaction.annotation.Transactional
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));
    
    System.out.println("user"+user);

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    
    if (strRoles == null) {
    	  Role userRole = roleRepository.findByName(ERole.ROLE_USER)
    	      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    	  roles.add(userRole);
    	} else {
    	  strRoles.forEach(role -> {
    	    Role foundRole = roleRepository.findByName(ERole.valueOf(role)).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    	    roles.add(foundRole);
    	  });
    	}
    
    System.out.println(strRoles+"roles");
    System.out.println(roles+"roles new");

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    user.setCompanyId(signUpRequest.getCompanyId());
    user.setSupervisor(signUpRequest.getSupervisor());
    user.setDesignation(signUpRequest.getDesignation());

    System.out.println(user+"user now");
    //userRepository.save(user);
    
    try {
    	  userRepository.save(user);
    	} catch (Exception e) {
    	  e.printStackTrace();
    	}


    //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    
    MessageResponse messageResponse = new MessageResponse("User registered successfully!");
    //return ResponseEntity.ok(new BaseResponseAuth<MessageResponse>("User registered successfully!", "200", messageResponse));
    return ResponseEntity.ok(new BaseResponseAuth<>("User registered successfully!", "200", null));


  }

}
*/