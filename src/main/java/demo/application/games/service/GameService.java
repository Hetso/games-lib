package demo.application.games.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return gameRepository.getAll();
    }

    public GameEntity get(long id) {
        return gameRepository.get(id);
    }
}
