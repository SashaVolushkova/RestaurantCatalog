package org.example.user.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "пользователь c таким именем или почтой уже существует")
public class NonUniqueUserException extends RuntimeException{
    public NonUniqueUserException(String message) {
        super(message);
    }
}
