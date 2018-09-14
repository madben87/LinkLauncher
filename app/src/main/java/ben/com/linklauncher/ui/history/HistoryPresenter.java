package ben.com.linklauncher.ui.history;

import ben.com.linklauncher.core.Presenter;

public interface HistoryPresenter<V extends HistoryView> extends Presenter<V> {

    void updateList();
}
