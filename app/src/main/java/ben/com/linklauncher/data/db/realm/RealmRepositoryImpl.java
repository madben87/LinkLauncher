package ben.com.linklauncher.data.db.realm;

import java.util.List;

import ben.com.linklauncher.data.model.LinkModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class RealmRepositoryImpl implements RealmRepository<LinkModel> {


    @Override
    public Observable<List<LinkModel>> getList(Class<LinkModel> cls) {
        return Observable.just(cls)
                .flatMap(new Function<Class<LinkModel>, ObservableSource<? extends List<LinkModel>>>() {
                    @Override
                    public ObservableSource<? extends List<LinkModel>> apply(Class<LinkModel> wordClass) throws Exception {
                        return Observable.just(RealmDBHelper.getLinksList());
                    }
                });
    }

    @Override
    public Observable<LinkModel> getItem(final long id) {
        return Observable.just(LinkModel.class)
                .flatMap(new Function<Class<LinkModel>, Observable<? extends LinkModel>>() {
                    @Override
                    public Observable<? extends LinkModel> apply(Class<LinkModel> wordClass) throws Exception {
                        return Observable.just(RealmDBHelper.getLink(id));
                    }
                });
    }

    @Override
    public Observable<LinkModel> addNewItem(final LinkModel model) {
        return Observable.just(model.getClass())
                .flatMap(new Function<Class<? extends LinkModel>, Observable<? extends LinkModel>>() {
                    @Override
                    public Observable<? extends LinkModel> apply(Class<? extends LinkModel> aClass) throws Exception {
                        return Observable.just(RealmDBHelper.addLink(model));
                    }
                });
    }

    @Override
    public void addList(List<LinkModel> list) {

    }

    @Override
    public Observable<LinkModel> updateItem(final LinkModel model) {
        return Observable.just(model.getClass())
                .flatMap(new Function<Class<? extends LinkModel>, Observable<? extends LinkModel>>() {
                    @Override
                    public Observable<? extends LinkModel> apply(Class<? extends LinkModel> aClass) throws Exception {
                        return Observable.just(RealmDBHelper.updateLink(model));
                    }
                });
    }

    @Override
    public void updateList(List<LinkModel> list) {

    }

    @Override
    public Observable<Boolean> removeItem(final LinkModel model) {
        return Observable.just(RealmDBHelper.deleteLink(model));
    }
}
