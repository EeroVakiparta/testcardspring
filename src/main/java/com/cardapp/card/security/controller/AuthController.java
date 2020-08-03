package com.cardapp.card.security.controller;
import com.cardapp.card.security.dto.JwtResponse;
import com.cardapp.card.security.dto.LoginRequest;
import com.cardapp.card.security.dto.RoleResponse;
import com.cardapp.card.security.dto.SignUpRequest;
import com.cardapp.card.security.repository.entity.User;
import com.cardapp.card.security.service.UserService;
import com.cardapp.card.security.utils.JwtUtils;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Api(tags = "Auth Api", value = "/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    JwtUtils jwtUtils;

    UserService userService;

    AuthenticationManager authenticationManager;


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(JwtResponse.builder().id(userDetails.getId()).username(userDetails.getUsername())
                .accessToken(jwtUtils.generateJwtToken(authentication)).tokenType("Bearer")
                .roles(userDetails.getRoles().stream().map(r -> {
                    RoleResponse response = new RoleResponse();
                    response.setName(r.getName());
                    response.setId(r.getId());
                    return response;
                }).collect(Collectors.toList())).build());
    }

    @PostMapping(value = "/signup")
    public final ResponseEntity<Void> signUp(@Valid @RequestBody final SignUpRequest request) {
        userService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
