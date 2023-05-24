package com.paulo.apipokedex.services;

import com.paulo.apipokedex.mapper.UserResponseMapper;
import com.paulo.apipokedex.model.User;
import com.paulo.apipokedex.record.UserRecord;
import com.paulo.apipokedex.repository.UserRepository;
import com.paulo.apipokedex.record.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<UserResponse> getAllUser() {
       return this.userRepository.findAll()
                .stream().map(UserResponseMapper::transformUserToUserResponse)
                .toList();
    }

    public Optional<UserResponse> getById(Long id) {
        return this.userRepository.findById(id)
                .map(UserResponseMapper::transformUserToUserResponse);
    }

    public void registerUser(UserRecord userRecord) {

        userRepository.findByEmail(userRecord.email()).ifPresent(user -> {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"\"message\": \"Usuário ja Existe\"");
        });
        User user = new User();


        user.setEmail(userRecord.email());
        user.setName(userRecord.name());
        user.setPassword(encoder.encode(userRecord.password()));
        user.setGender(userRecord.gender());

        userRepository.save(user);
    }
}
