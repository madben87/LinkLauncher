package ben.com.linklauncher.ui.history;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;
import io.reactivex.disposables.CompositeDisposable;

public class HistoryPresenterImpl implements HistoryPresenter<HistoryView> {

    private HistoryView view;

    @Inject
    Context context;

    private CompositeDisposable disposable;

    public HistoryPresenterImpl() {
        App.getAppInjector().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void attachView(HistoryView historyView) {
        this.view = historyView;
    }

    @Override
    public void detachView() {
        view = null;
        disposable.clear();
    }

    public static final String BASE_URL = "content://ben.com.linklauncher.linkprovider/link";

    @Override
    public void updateList() {
        Cursor cursor = context.getContentResolver().query(Uri.parse(BASE_URL), null, null, null, null);
        if (cursor != null) {
            List<LinkModel> list = LinkUtil.listModelFromCursor(cursor);
            view.updateListView(list);
        }
    }

    @Override
    public void sendLink(LinkModel model) {
        if (model != null) {
            Objects.requireNonNull(context).sendBroadcast(LinkUtil.getRequest(model, App.SHOW_HISTORY_LINK));
        }
    }
}
