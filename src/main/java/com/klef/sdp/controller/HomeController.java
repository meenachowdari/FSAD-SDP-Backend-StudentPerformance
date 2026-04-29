package com.klef.sdp.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = {"http://localhost:2023", "https://*.vercel.app", "https://*.up.railway.app"})
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "message", "Spring Boot SDP backend is running"
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "healthy"));
    }
}