package ben.com.linklauncher.util;

public class MessageEvent {

    public static final int UPDATED_DB = 1;
    public static final int UPDATED_VIEW= 2;
    public static final int SORT_HISTORY= 3;

    public final int msg;

    public MessageEvent(int message) {
        this.msg = message;
    }
}
