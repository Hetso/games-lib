package demo.application.games.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.application.games.exception.GameAlreadyExistsException;
import demo.application.games.exception.GameNotFoundException;
import demo.application.games.exception.InvalidGameFieldsException;
import demo.application.games.model.GameEntity;
import demo.application.games.repository.game.GameRepository;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameEntity> getAll() {
        return gameRepository.findAll();
    }

    public GameEntity get(long id) {
        return gameRepository.findById(id);
    }

    public void create(GameEntity entity) {
        validateGameFields(entity);

        final GameEntity currentGame = gameRepository.findByName(entity.getName());
        if(currentGame != null) {
            throw new GameAlreadyExistsException(currentGame.getId());
        }

        gameRepository.insert(entity);
    }

    public void update(GameEntity entity) {
        validateGameFields(entity);

        if(gameRepository.findById(entity.getId()) == null) {
            throw new GameNotFoundException(entity.getId());
        }

        gameRepository.update(entity);
    }

    private void validateGameFields(GameEntity entity) {
        if(entity.getName() == null || entity.getName().isBlank()) {
            throw new InvalidGameFieldsException("Name");
        }
    }

    public void delete(long id) {
        if(gameRepository.findById(id) == null) {
            throw new GameNotFoundException(id);
        }

        gameRepository.delete(id);
    }
}
