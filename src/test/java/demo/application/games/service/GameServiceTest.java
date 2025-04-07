package demo.application.games.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import demo.application.games.exception.GameAlreadyExistsException;
import demo.application.games.exception.GameExceptionCode;
import demo.application.games.exception.GameNotFoundException;
import demo.application.games.exception.InvalidGameFieldsException;
import demo.application.games.model.GameEntity;
import demo.application.games.model.GameStatus;
import demo.application.games.repository.game.GameRepository;
import demo.application.shared.model.Interest;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(gameRepository);
    }

    @Test
    void create_ValidGame_ShouldInsertGame() {
        final String gameName = "Test Game";

        GameEntity game = new GameEntity(0, gameName, Interest.LOW, GameStatus.FINISHED);
        when(gameRepository.findByName(gameName)).thenReturn(null);

        gameService.create(game);

        verify(gameRepository).insert(game);
    }

    @Test
    void create_ExistingGameName_ShouldThrowGameAlreadyExistsException() {
        final String gameName = "Test Game";
        final long gameId = 1l;

        GameEntity existingGame = new GameEntity(gameId, gameName, Interest.LOW, GameStatus.FINISHED);
        GameEntity newGame = new GameEntity(0, gameName, Interest.MEDIUM, GameStatus.NOW_OWNED);

        when(gameRepository.findByName(gameName)).thenReturn(existingGame);

        GameAlreadyExistsException exception = assertThrows(
            GameAlreadyExistsException.class,
            () -> gameService.create(newGame)
        );

        assertEquals(GameExceptionCode.GAME_ALREADY_EXISTS.toString(), exception.getCode());
        verify(gameRepository, never()).insert(any());
    }

    @Test
    void create_NullGameName_ShouldThrowInvalidGameFieldsException() {
        GameEntity game = new GameEntity(0, null, Interest.HIGH, GameStatus.NOW_OWNED);

        InvalidGameFieldsException exception = assertThrows(
            InvalidGameFieldsException.class,
            () -> gameService.create(game)
        );

        assertEquals(GameExceptionCode.INVALID_GAME_FIELDS.toString(), exception.getCode());
        verify(gameRepository, never()).findByName(any());
        verify(gameRepository, never()).insert(any());
    }

    @Test
    void create_BlankGameName_ShouldThrowInvalidGameFieldsException() {
        GameEntity game = new GameEntity(0, "    ", Interest.HIGH, GameStatus.NOW_OWNED);

        InvalidGameFieldsException exception = assertThrows(
            InvalidGameFieldsException.class,
            () -> gameService.create(game)
        );

        assertEquals(GameExceptionCode.INVALID_GAME_FIELDS.toString(), exception.getCode());
        verify(gameRepository, never()).findByName(any());
        verify(gameRepository, never()).insert(any());
    }

    @Test
    void update_ValidGame_ShouldUpdateGame() {
        final long gameId = 1L;

        GameEntity game = new GameEntity(gameId, "Test Game", Interest.LOW, GameStatus.FINISHED);
        when(gameRepository.findById(gameId)).thenReturn(game);

        gameService.update(game);

        verify(gameRepository).update(game);
    }

    @Test
    void update_InvalidGameId_ShouldThrowGameNotFoundException() {
        final long gameId = 1L;

        GameEntity game = new GameEntity(gameId, "Test Game", Interest.LOW, GameStatus.FINISHED);
        when(gameRepository.findById(gameId)).thenReturn(null);
        
        GameNotFoundException exception = assertThrows(
            GameNotFoundException.class,
            () -> gameService.update(game)
        );

        assertEquals(GameExceptionCode.GAME_NOT_FOUND.toString(), exception.getCode());
        verify(gameRepository, never()).update(any());
    }

    @Test
    void update_NullGameName_ShouldThrowInvalidGameFieldsException() {
        final long gameId = 1l;

        GameEntity game = new GameEntity(gameId, null, Interest.HIGH, GameStatus.NOW_OWNED);

        InvalidGameFieldsException exception = assertThrows(
            InvalidGameFieldsException.class,
            () -> gameService.update(game)
        );

        assertEquals(GameExceptionCode.INVALID_GAME_FIELDS.toString(), exception.getCode());
        verify(gameRepository, never()).findById(gameId);
        verify(gameRepository, never()).update(any());
    }

    @Test
    void update_BlankGameName_ShouldThrowInvalidGameFieldsException() {
        final long gameId = 1l;
        GameEntity game = new GameEntity(gameId, "    ", Interest.HIGH, GameStatus.NOW_OWNED);

        InvalidGameFieldsException exception = assertThrows(
            InvalidGameFieldsException.class,
            () -> gameService.update(game)
        );

        assertEquals(GameExceptionCode.INVALID_GAME_FIELDS.toString(), exception.getCode());
        verify(gameRepository, never()).findById(gameId);
        verify(gameRepository, never()).update(any());
    }

    @Test
    void delete_ValidGame_ShouldDeleteGame() {
        final long gameId = 1L;

        when(gameRepository.findById(gameId)).thenReturn(new GameEntity(gameId, "Game to delete", Interest.HIGH, GameStatus.FINISHED));
        gameService.delete(gameId);

        verify(gameRepository).delete(gameId);
    }

    @Test
    void delete_InvalidGameId_ShouldThrowGameNotFoundException() {
        final long gameId = 1L;

        when(gameRepository.findById(gameId)).thenReturn(null);
        
        GameNotFoundException exception = assertThrows(
            GameNotFoundException.class,
            () -> gameService.delete(gameId)
        );

        assertEquals(GameExceptionCode.GAME_NOT_FOUND.toString(), exception.getCode());
        verify(gameRepository, never()).delete(gameId);
    }
}