package entity;

public enum BookingState {
    ACTIVE(1, "STILL LIVING"),
    LEFT(0, "LEFT");

    private final int code;
    private final String displayName;

    BookingState(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BookingState fromCode(int code) {
        for (BookingState s : BookingState.values()) {
            if (s.getCode() == code) return s;
        }
        return ACTIVE; // default
    }
}
