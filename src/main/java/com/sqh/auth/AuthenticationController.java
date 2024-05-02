package com.sqh.auth;

import com.sqh.dto.AuthenticationRequest;
import com.sqh.dto.AuthenticationResponse;
import com.sqh.dto.RegisterRequest;
import com.sqh.repository.UserRepository;
import com.sqh.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public String register(@Validated @RequestBody RegisterRequest request){

        try {
            if(userRepository.findByEmail(request.getEmail()).isPresent()){

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email address already registered");
            }
            ResponseEntity.ok(service.register(request));
            return ("User created successfully");

        } catch (ResponseStatusException ex) {
            return ex.getReason();
        }
    }


/*@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) {

        AuthenticationRequest token = new AuthenticationRequest(request.getEmail(), request.getPassword());
        service.authenticate(token);
        String jwt = String.valueOf(service.authenticate(request));
        return ResponseEntity.ok(jwt);
    }*/



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

            return ResponseEntity.ok(service.authenticate(request));

    }

   /* @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = service.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthenticationResponse("User not found"));
        }
    }*/
}




