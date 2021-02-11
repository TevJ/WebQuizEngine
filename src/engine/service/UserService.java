package engine.service;

import engine.dto.UserDto;
import engine.entity.User;
import engine.entity.UserMapper;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerUser(UserDto userDto) {
        var user = userMapper.mapUserDtoToUser(userDto);
        checkEmail(user);
        userRepository.save(user);
    }

    private void checkEmail(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserEmailExistsException();
        }
    }
}
