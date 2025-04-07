package demo.application.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {
    
    public CustomException(String message) {
        super(message);
    }

    public abstract String getCode();

    public abstract HttpStatus getHttpStatus();
}
