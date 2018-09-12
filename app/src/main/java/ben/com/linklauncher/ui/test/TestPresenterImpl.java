package ben.com.linklauncher.ui.test;

import android.content.Context;
import android.content.Intent;

import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.db.Repository;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;

public class TestPresenterImpl implements TestPresenter<TestView> {

    @Inject
    Context context;
    @Inject
    Repository repository;

    private TestView view;

    public TestPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void sendLink(LinkModel model) {
        if (model != null) {
            //Intent intent = new Intent(App.SHOW_LINK);
            //intent.putExtra(App.KEY_LINK, model/*.getLink()*/);
            //intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            Objects.requireNonNull(context).sendBroadcast(LinkUtil.getRequest(model));
        }
    }

    @Override
    public void attachPresenter(TestView testView) {
        this.view = testView;
    }

    @Override
    public void detachPresenter() {
        view = null;
    }
}
