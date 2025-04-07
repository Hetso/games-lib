package demo.application.games.exception;

import org.springframework.http.HttpStatus;

import demo.application.shared.exception.CustomException;

public class GameNotFoundException extends CustomException {

    public GameNotFoundException(Long id) {
        super("Game ID " + id + " not found.");
    }

    @Override
    public String getCode() {
        return GameExceptionCode.GAME_NOT_FOUND.toString();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
