package org.example.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "пользователь c таким именем или почтой уже существует")
public class NonUniqueUserException extends RuntimeException{
    public NonUniqueUserException(String message) {
        super(message);
    }
}
