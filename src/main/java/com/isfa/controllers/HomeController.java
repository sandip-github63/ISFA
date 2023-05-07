/**package com.isfa.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.models.BaseResponseAuth;
import com.isfa.payload.response.MessageResponse;


@RestController
@RequestMapping("/api/home")
public class HomeController {



       @GetMapping("/attendance")
    public ResponseEntity<String> getAttendancePage() {
        return ResponseEntity.ok("/api/attendance/mark");
    }

    @GetMapping("/my-activity")
    public ResponseEntity<String> getMyActivityPage() {
        return ResponseEntity.ok("/api/my-activity");
    }

    @GetMapping("/beat-plan")
    public ResponseEntity<String> getBeatPlanPage() {
        return ResponseEntity.ok("/api/beat-plan");
    }

    @GetMapping("/learnings")
    public ResponseEntity<String> getLearningsPage() {
        return ResponseEntity.ok("/api/learnings");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
      SecurityContextHolder.clearContext();
      //return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
      MessageResponse messageResponse = new MessageResponse("User logged out successfully!");
      return ResponseEntity.ok(new BaseResponseAuth<MessageResponse>("", "200", messageResponse));

    }
}
*/
