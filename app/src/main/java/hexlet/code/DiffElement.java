package hexlet.code;

public class DiffElement {

    public enum DiffType {
        CHANGED,
        UNCHANGED,
        ADDED,
        DELETED
    }

    private final String key;
    private final Object value;
    private final Object value2;
    private final DiffType param;

    public DiffElement(String keyDiff, Object valueDiff, DiffType paramDiff) {
        this.key = keyDiff;
        this.value = valueDiff;
        this.param = paramDiff;
        this.value2 = null;
    }

    public DiffElement(String keyDiff, Object valueDiff, Object valueDiff2, DiffType paramDiff) {
        this.key = keyDiff;
        this.value = valueDiff;
        this.value2 = valueDiff2;
        this.param = paramDiff;
    }

    public final String getKey() {
        return key;
    }

    public final Object getValue() {
        return value;
    }

    public final Object getValue2() {
        return value2;
    }

    public final DiffType getParam() {
        return param;
    }
}
