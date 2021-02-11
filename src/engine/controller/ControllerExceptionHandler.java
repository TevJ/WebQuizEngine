package engine.controller;

import engine.service.UserEmailExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "Could not find resource";
    private static final String USER_ALREADY_EXISTS_MESSAGE = "A user with this email already exists";

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND_MESSAGE)
    public Map<String, String> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler(UserEmailExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = USER_ALREADY_EXISTS_MESSAGE)
    public Map<String, String> handleUserEmailExistsException(UserEmailExistsException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", USER_ALREADY_EXISTS_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}
