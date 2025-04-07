package demo.application.games.repository.game;

import java.util.List;

import org.springframework.stereotype.Repository;

import demo.application.games.model.GameEntity;

@Repository
public class GameRepository {

    private final GameMapper mapper;

    public GameRepository(GameMapper mapper) {
        this.mapper = mapper;
    }

    public GameEntity get(long id) {
        return mapper.findById(id);
    }

    public List<GameEntity> getAll() {
        return mapper.findAll();
    }
   
}
