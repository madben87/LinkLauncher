package ben.com.linklauncher.ui.history;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.db.Repository;
import ben.com.linklauncher.data.db.realm.RealmRepository;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HistoryPresenterImpl implements HistoryPresenter<HistoryView> {

    private HistoryView view;

    @Inject
    Repository repository;
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

    /*@Override
    public void updateList() {
        disposable.add((Disposable) repository.getList(LinkModel.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<LinkModel>>() {
                    @Override
                    public void onNext(List<LinkModel> list) {
                        if (list != null && list.size() > 0 && view != null) {
                            view.updateListView(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }*/

    public static final String BASE_URL = "content://ben.com.linklauncher.linkprovider/link";

    @Override
    public void updateList() {
        Cursor cursor = context.getContentResolver().query(Uri.parse(BASE_URL + "100"), null, null, null, null);

    }

    @Override
    public void sendLink(LinkModel model) {
        if (model != null) {
            Objects.requireNonNull(context).sendBroadcast(LinkUtil.getRequest(model, App.SHOW_HISTORY_LINK));
        }
    }
}
