package demo.application.games.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import demo.application.shared.model.Interest;

public class GameEntity {

    private long id;
    private String name;
    private Interest interest;
    private GameStatus status;
    private List<GameTag> tags;

    public GameEntity() {
        this.tags = new ArrayList<>();
    }

    public GameEntity(long id, GameEntity entity) {
        this.id = id;
        this.name = entity.getName();
        this.interest = entity.getInterest();
        this.status = entity.getStatus();
        this.tags = entity.getTags();
    }

    public GameEntity(long id, String name, Interest interest, GameStatus status) {
        this.id = id;
        this.name = name;
        this.interest = interest;
        this.status = status;
        this.tags = new ArrayList<>();
    }

    public GameEntity(long id, String name, Interest interest, GameStatus status, List<GameTag> tags) {
        this.id = id;
        this.name = name;
        this.interest = interest;
        this.status = status;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Interest getInterest() {
        return interest;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<GameTag> getTags() {
        return Collections.unmodifiableList(tags);
    }
}
