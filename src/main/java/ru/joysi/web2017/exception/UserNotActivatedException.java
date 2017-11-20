package ru.joysi.web2017.exception;

public class UserNotActivatedException  extends RuntimeException {
    public UserNotActivatedException(String message) {
        super(message);
    }
}
