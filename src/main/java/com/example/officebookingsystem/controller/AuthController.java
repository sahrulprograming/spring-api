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
import com.example.officebookingsystem.domain.implementation.UserDetailImpl;

import com.example.officebookingsystem.service.AuthService;
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
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @ApiOperation(value = "01 Signin", notes = "Endpoint untuk login ")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       return authService.signIn(loginRequest);
    }

    @ApiOperation(value = "01 Signup", notes = "Endpoint untuk mendaftar ")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return authService.signUp(signupRequest);
    }

    @ApiOperation(value = "01 Signout", notes = "Endpoint untuk logout ")
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(){
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}

