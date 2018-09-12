package ben.com.linklauncher.data.model;

public class LinkRequest {

    private long id;
    private String link;
    private int status;

    public LinkRequest(LinkModel model) {
        this.id = model.getId();
        this.link = model.getLink();
        this.status = setStatus(model.getStatus());
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public int getStatus() {
        return status;
    }

    private int setStatus(int status) {
        if (status == Status.ACTIVE.getValue()) {
            return 1;
        }else if (status == Status.ERROR.getValue()) {
            return 2;
        }else if (status == Status.UNKNOWN.getValue()) {
            return 3;
        }

        return -1;
    }
}
