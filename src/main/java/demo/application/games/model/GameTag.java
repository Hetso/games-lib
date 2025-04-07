package demo.application.games.model;

public class GameTag {
    
    private long id;
    private String name;

    public GameTag(String name) {
        this(0, name);
    }

    public GameTag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    };
}
