package ben.com.linklauncher.data.model;

public enum Status {

    ACTIVE(1), ERROR(2), UNKNOWN(3);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

