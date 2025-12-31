package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.config.CustomUserDetailsService;
import com.davideleonino.palestra.dto.request.LoginRequest;
import com.davideleonino.palestra.dto.response.JwtAuthResponse;
import com.davideleonino.palestra.dto.response.UserProfileResponse;
import com.davideleonino.palestra.model.Role;
import com.davideleonino.palestra.model.User;
import com.davideleonino.palestra.repository.UserRepository;
import com.davideleonino.palestra.config.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.davideleonino.palestra.dto.request.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Login → ritorna JWT
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    // ✅ Profilo dell’utente autenticato (me)
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        UserProfileResponse response = new UserProfileResponse(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // 👈 Assegna il ruolo USER
        userRepository.save(user);
        return ResponseEntity.ok("Utente registrato");
    }

}
