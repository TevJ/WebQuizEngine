package engine.controller;

import engine.dto.UserDto;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerNewUser(@Valid @RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }
}
