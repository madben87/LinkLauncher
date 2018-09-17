package ben.com.linklauncher.ui.test;

import android.content.Context;

import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;

public class TestPresenterImpl implements TestPresenter<TestView> {

    @Inject
    Context context;

    private TestView view;

    public TestPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void sendLink(LinkModel model) {
        if (model != null) {
            Objects.requireNonNull(context).sendBroadcast(LinkUtil.getRequest(model, App.SHOW_LINK));
        }
    }

    @Override
    public void attachView(TestView testView) {
        this.view = testView;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
