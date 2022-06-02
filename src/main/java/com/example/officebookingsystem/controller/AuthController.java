package com.example.officebookingsystem.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.officebookingsystem.domain.dto.request.LoginRequest;
import com.example.officebookingsystem.domain.dto.request.SignupRequest;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.dto.response.UserInfoResponse;
import com.example.officebookingsystem.domain.entity.Role;
import com.example.officebookingsystem.domain.entity.User;
import com.example.officebookingsystem.domain.model.ERole;
import com.example.officebookingsystem.domain.repository.RoleRepository;
import com.example.officebookingsystem.domain.repository.UserRepository;
import com.example.officebookingsystem.security.jwt.JwtUtils;
import com.example.officebookingsystem.security.service.UserDetailImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
// Custom Penamaan Controller Pada Doc Swagger
@Api(tags = "Auth Controller", description = "Authentication")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @ApiOperation(value = "01 Signin", notes = "Endpoint untuk login ")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetail);
        List<String> roles = userDetail.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetail.getId(),
                        userDetail.getUsername(),
                        userDetail.getEmail(),
                        roles
                ));
    }

    @ApiOperation(value = "01 Signup", notes = "Endpoint untuk mendaftar ")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error:Username is Already taken"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is Already in User !"));
        }
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                        if (Objects.equals(role, "admin")) {
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                        } else {
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                            roles.add(userRole);
                        }
                        ;
                    }
            );
        }
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @ApiOperation(value = "01 Signout", notes = "Endpoint untuk logout ")
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(){
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}

