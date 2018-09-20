package ben.com.linklauncher.ui.main;

import org.greenrobot.eventbus.EventBus;

import ben.com.linklauncher.util.MessageEvent;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView mainView;

    @Override
    public void sortAction() {
        EventBus.getDefault().post(new MessageEvent(MessageEvent.SORT_HISTORY));
    }

    @Override
    public void attachView(MainView mvpView) {
        this.mainView = mvpView;
    }

    @Override
    public void detachView() {
        mainView = null;
    }
}
