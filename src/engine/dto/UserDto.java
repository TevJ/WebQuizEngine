package engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @ValidEmail
    @NotNull
    @NotEmpty
    private final String email;
    @Size(min = 5)
    private final String password;

    public UserDto(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
    ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
