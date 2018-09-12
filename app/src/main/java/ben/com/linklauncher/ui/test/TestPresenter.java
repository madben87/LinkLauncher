package ben.com.linklauncher.ui.test;

import ben.com.linklauncher.core.Presenter;
import ben.com.linklauncher.data.model.LinkModel;

public interface TestPresenter<V extends TestView> extends Presenter<V> {

    void sendLink(LinkModel model);
}
