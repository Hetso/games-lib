package demo.application.games.exception;

import org.springframework.http.HttpStatus;

import demo.application.shared.exception.CustomException;

public class InvalidGameFieldsException extends CustomException {

    public InvalidGameFieldsException(final String field) {
        super("Invalid field " + field);
    }

    @Override
    public String getCode() {
        return GameExceptionCode.INVALID_GAME_FIELDS.toString();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
