package com.paulo.apipokedex.mapper;

import com.paulo.apipokedex.model.User;
import com.paulo.apipokedex.record.UserRecord;
import com.paulo.apipokedex.record.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserResponseMapper {

    public static UserResponse transformUserToUserResponse(User user) {
        return new UserResponse(user.getName(), user.getEmail(), user.getGender(), user.getPokemons());
    }


}
