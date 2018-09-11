package ben.com.linklauncher.data.db.realm;

import ben.com.linklauncher.data.db.Repository;
import io.realm.RealmObject;

public interface RealmRepository<D extends RealmObject> extends Repository<D> {
}
