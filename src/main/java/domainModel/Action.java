package domainModel;

public enum Action {
    INVENT(10),
    HAVE_FUN(5);

    private final int value;

    Action(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
