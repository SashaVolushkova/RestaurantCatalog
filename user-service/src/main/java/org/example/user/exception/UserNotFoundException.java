package org.example.user.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "пользователь не найден")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
