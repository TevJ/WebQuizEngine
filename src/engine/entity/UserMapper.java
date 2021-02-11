package engine.entity;

import engine.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword())
        );
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getPassword()
        );
    }
}
