package demo.application.games.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.application.games.model.GameEntity;
import demo.application.games.model.GameStatus;
import demo.application.games.repository.game.GameRepository;
import demo.application.shared.model.Interest;
import demo.application.shared.repository.BaseRepositoryTest;

class GameRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    GameEntity defaultGame;

    @BeforeEach
    private void setDefaultGame() {
        defaultGame = new GameEntity(0, "Default test game", Interest.LOW, GameStatus.OWNED_NOT_PLAYED);
    }

    @Test
    void test_insert_game() {
        int rows = gameRepository.insert(defaultGame);

        assertEquals(rows, 1);
        assertTrue(defaultGame.getId() > 0);
    }

    @Test
    void test_findById_game() {
        gameRepository.insert(defaultGame);

        final GameEntity game = gameRepository.findById(defaultGame.getId());

        assertNotNull(game);
    }

    @Test
    void test_findAll_game() {
        final List<GameEntity> games = gameRepository.findAll();

        assertEquals(games.size(), 2);
    }

    @Test
    void test_findByName_game() {
        gameRepository.insert(defaultGame);

        final GameEntity game = gameRepository.findByName(defaultGame.getName());

        assertNotNull(game);
    }

    @Test
    void test_update_game() {
        final String newName = "Updated default test game";
        gameRepository.insert(defaultGame);

        assertNotEquals(newName, defaultGame.getName());

        gameRepository.update(new GameEntity(defaultGame.getId(), newName, Interest.LOW, GameStatus.OWNED_NOT_PLAYED));

        assertEquals(newName, gameRepository.findById(defaultGame.getId()).getName());
    }

    @Test
    void test_delete_game() {
        gameRepository.insert(defaultGame);

        assertNotNull(gameRepository.findById(defaultGame.getId()));
        gameRepository.delete(defaultGame.getId());
        assertNull(gameRepository.findById(defaultGame.getId()));
    }
    
}
