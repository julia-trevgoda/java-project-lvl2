package hexlet.code;

public class DiffElement {

    public enum DiffType {
        ADD,
        REMOVE,
        EQUALS,
        ADD_ONE,
        REMOVE_ONE
    }

    private final String key;
    private final Object value;
    private final DiffType param;

    public DiffElement(String keyDiff, Object valueDiff, DiffType paramDiff) {
        this.key = keyDiff;
        this.value = valueDiff;
        this.param = paramDiff;
    }

    public final String getKey() {
        return key;
    }

    public final Object getValue() {
        return value;
    }

    public final DiffType getParam() {
        return param;
    }
}
