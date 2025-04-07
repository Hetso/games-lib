package demo.application.games.exception;

import org.springframework.http.HttpStatus;

import demo.application.shared.exception.CustomException;

public class GameAlreadyExistsException extends CustomException {

    public GameAlreadyExistsException(long id) {
        super("Game already exists with id " + id);
    }

    @Override
    public String getCode() {
        return GameExceptionCode.GAME_ALREADY_EXISTS.toString();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
