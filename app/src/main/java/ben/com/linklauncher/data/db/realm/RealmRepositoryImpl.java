package ben.com.linklauncher.data.db.realm;

import java.util.List;

import io.reactivex.Observable;

public class RealmRepositoryImpl implements RealmRepository {

    @Override
    public Observable<List> getList(Class cls) {
        return null;
    }

    @Override
    public Observable getItem(long id) {
        return null;
    }

    @Override
    public Observable addNewItem(Object o) {
        return null;
    }

    @Override
    public void addList(List list) {

    }

    @Override
    public void updateItem(Object o) {

    }

    @Override
    public void updateList(List list) {

    }

    @Override
    public void removeItem(long id) {

    }
}
