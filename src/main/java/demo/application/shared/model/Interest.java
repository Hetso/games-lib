package demo.application.shared.model;

public enum Interest {
    VERY_LOW(1),
    LOW(2),
    MEDIUM(3),
    HIGH(4),
    VERY_HIGH(5);

    private final int value;

    Interest(int value) {
        this.value = value;
    };

    public int intValue() {
        return this.value;
    }
}
