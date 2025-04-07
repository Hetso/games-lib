package demo.application.games.repository.game;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.application.games.model.GameEntity;

@Mapper
public interface GameMapper {
    
    public GameEntity findById(@Param("id") long id);

    public List<GameEntity> findAll();
}
