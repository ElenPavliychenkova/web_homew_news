package project.web.app.exceptions.auth;

public class AuthenticationException extends  RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}

