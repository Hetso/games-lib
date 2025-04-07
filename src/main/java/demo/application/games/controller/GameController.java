package demo.application.games.controller;

import org.springframework.web.bind.annotation.RestController;

import demo.application.games.exception.GameNotFoundException;
import demo.application.games.model.GameEntity;
import demo.application.games.service.GameService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all games", description = "Get full games details")
    public ResponseEntity<List<GameEntity>> getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a game by id", description = "Get full game details if exists")
    public ResponseEntity<GameEntity> getGame(@PathVariable long id) {
        final GameEntity game = gameService.get(id);

        if (game == null) {
            throw new GameNotFoundException(id);
        }

        return ResponseEntity.ok(game);
    }

    @PostMapping("/")
    @Operation(summary = "Create a game", description = "Create a game and return with ID")
    public ResponseEntity<GameEntity> createGame(@RequestBody GameEntity entity) {
        gameService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/")
    @Operation(summary = "Update a game", description = "Update an existing game by its ID")
    public ResponseEntity<GameEntity> updateGame(@RequestBody GameEntity entity) {
        gameService.update(entity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a game", description = "Delete a game by its ID")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
