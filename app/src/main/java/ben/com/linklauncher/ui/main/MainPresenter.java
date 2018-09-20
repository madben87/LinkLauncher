package ben.com.linklauncher.ui.main;

import ben.com.linklauncher.core.Presenter;

public interface MainPresenter<V extends MainView> extends Presenter<V> {
    void sortAction();
}
