package demo.application.games.model;

public enum GameStatus {
    NOW_OWNED(1),
    OWNED_NOT_PLAYED(2),
    STARTED_NOT_FINISHED(3),
    FINISHED(4);

    private final int value;

    GameStatus(int value) {
        this.value = value;
    }

    public int intValue() {
        return this.value;
    }
}
