package com.paulo.apipokedex.services;

import com.paulo.apipokedex.model.User;
import com.paulo.apipokedex.record.UserRecord;
import com.paulo.apipokedex.repository.UserRepository;
import com.paulo.apipokedex.record.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .stream().map(UserResponse::new)
                .toList();
    }

    public Optional<UserResponse> getById(Long id) {
        return this.userRepository.findById(id)
                .map(UserResponse::new);
    }

    public void registerUser(UserRecord userRecord) {
        userRepository.findByEmail(userRecord.email()).ifPresent(user -> {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"\"message\": \"Usuário ja Existe\"");
        });
        userRepository.save(createUser(userRecord));
    }

    private void updateProperties(User user, UserRecord updateUser) {
        UpdatePartial.updatePropererIfNotNull(user::setName, updateUser.name());
        UpdatePartial.updatePropererIfNotNull(user::setEmail, updateUser.email());
        UpdatePartial.updatePropererIfNotNull(user::setPassword, updateUser.password());
        UpdatePartial.updatePropererIfNotNull(user::setGender, updateUser.gender());
    }

    private User createUser(UserRecord userRecord){
        User user = new User();

        user.setEmail(userRecord.email());
        user.setName(userRecord.name());
        user.setPassword(encoder.encode(userRecord.password()));
        user.setGender(userRecord.gender());

        return user;
    }
}
