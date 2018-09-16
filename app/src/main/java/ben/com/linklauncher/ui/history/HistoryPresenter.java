package ben.com.linklauncher.ui.history;

import ben.com.linklauncher.core.Presenter;
import ben.com.linklauncher.data.model.LinkModel;

public interface HistoryPresenter<V extends HistoryView> extends Presenter<V> {

    void updateList();
    void sendLink(LinkModel model);
}
