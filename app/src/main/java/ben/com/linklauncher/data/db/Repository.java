package ben.com.linklauncher.data.db;

import java.util.List;

public interface Repository<D> {

    List<D> getItemsList();

    D getItem(long id);

    long addItem(D item);

    long updateItem(D item);

    boolean deleteItem(D item);
}
