package demo.application.games.repository.game;

import java.util.List;

import demo.application.games.model.GameEntity;

public interface GameRepository {

    public List<GameEntity> findAll();
    
    public GameEntity findById(final long id);

    public GameEntity findByName(final String name);

    public int insert(final GameEntity entity);

    public int update(final GameEntity entity);

    public int delete(final long id);
}
