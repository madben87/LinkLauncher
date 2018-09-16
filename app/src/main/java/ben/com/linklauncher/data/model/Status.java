package ben.com.linklauncher.data.model;

import ben.com.linklauncher.R;

public enum Status {

    ACTIVE(R.color.linkActive), ERROR(R.color.linkError), UNKNOWN(R.color.linkUnknown);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

