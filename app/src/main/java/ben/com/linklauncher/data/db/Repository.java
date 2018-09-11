package ben.com.linklauncher.data.db;

import java.util.List;

import io.reactivex.Observable;

public interface Repository<D> {

    Observable<List<D>> getList(Class<D> cls);

    Observable<D> getItem(long id);

    Observable<D> addNewItem(D d);

    void addList(List<D> list);

    void updateItem(D d);

    void updateList(List<D> list);

    void removeItem(long id);
}