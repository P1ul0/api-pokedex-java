package com.paulo.apipokedex.record;

import com.paulo.apipokedex.model.Pokemon;
import com.paulo.apipokedex.model.User;
import com.paulo.apipokedex.model.enums.Gender;
import jakarta.validation.constraints.NotNull;


import java.util.List;


public record UserResponse(
        @NotNull(message = "Campo Obrigátorio")
        String name,
        @NotNull(message = "Campo Obrigátorio")
        String email,
        @NotNull(message = "Campo Obrigátorio")
        Gender gender,
        @NotNull(message = "Campo Obrigátorio")
        List<Pokemon> pokemons
) {

}
