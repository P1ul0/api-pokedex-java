package com.paulo.apipokedex.controller;

import com.paulo.apipokedex.record.UserRecord;
import com.paulo.apipokedex.record.UserResponse;
import com.paulo.apipokedex.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/users", produces = {"application/json"})
public class UserController {
    @Autowired
    private UserServices userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser (){
        try{
            List<UserResponse> users = userService.getAllUser();
            return ResponseEntity.ok(users);

        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<UserResponse>> getById(@RequestHeader("Authorization") String request, Long id){
        try{
            Optional<UserResponse> user = userService.getById(id);
            return ResponseEntity.ok(user);
        }catch (Exception error){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserRecord userRecord){
        if (!userRecord.password().equals(userRecord.confirmPassword())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"\"message\": \"senhas incompativeis\"");
        }
        try{
            userService.registerUser(userRecord);
            return ResponseEntity.ok("\"message\": \"Usuário Criado Com Sucesso\"");
        }catch (Exception error){
            return  ResponseEntity.badRequest().body(error);
        }
    }
}
