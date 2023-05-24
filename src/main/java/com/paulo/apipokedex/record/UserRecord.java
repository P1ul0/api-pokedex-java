package com.paulo.apipokedex.record;

import com.paulo.apipokedex.model.User;
import com.paulo.apipokedex.model.enums.Gender;
import jakarta.validation.constraints.NotNull;

public record UserRecord(
        @NotNull(message = "Campo Obrigátorio")
        String name,

        @NotNull(message = "Campo Obrigátorio")
        String email,

        @NotNull(message = "Campo Obrigátorio")
        String password,

        @NotNull(message = "Campo Obrigátorio")
        String confirmPassword,

        @NotNull(message = "Campo Obrigátorio")
        Gender gender
) {

}
