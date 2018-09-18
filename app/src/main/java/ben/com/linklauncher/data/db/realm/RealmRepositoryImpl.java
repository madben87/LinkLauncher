package ben.com.linklauncher.data.db.realm;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;

import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.MessageEvent;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmRepositoryImpl implements RealmRepository<LinkModel> {

    private LinkModel result;
    private long resultId;

    @Override
    public List<LinkModel> getItemsList() {
        Realm realm = Realm.getDefaultInstance();

        List<LinkModel> resList = new RealmList<>();

        if (realm != null) {

            RealmResults<LinkModel> results = realm.where(LinkModel.class).findAll();

            resList = realm.copyFromRealm(results);

        }
        return resList;
    }

    @Override
    public LinkModel getItem(long id) {
        Realm realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            LinkModel model = realm.where(LinkModel.class).equalTo("id", id).findFirst();
            if (model != null) {
                result = realm.copyFromRealm(model);
            }

            realm.close();
        }

        return result;
    }

    @Override
    public long addItem(LinkModel item) {
        Realm realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {

            if (item != null) {
                LinkModel lm = realm.where(LinkModel.class).equalTo("id", item.getId()).findFirst();
                realm.beginTransaction();
                if (lm != null) {
                    lm.setStatus(item.getStatus());
                    resultId = realm.copyToRealmOrUpdate(lm).getId();
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                }else {
                    resultId = realm.copyToRealmOrUpdate(item).getId();
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                }
                realm.commitTransaction();
            }

            realm.close();
        }

        return resultId;
    }

    @Override
    public long updateItem(final LinkModel item) {
        Realm realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    LinkModel lm = realm.where(LinkModel.class).equalTo("id", item.getId()).findFirst();

                    if (lm != null) {
                        lm.setStatus(item.getStatus());

                        resultId = realm.copyFromRealm(realm.copyToRealmOrUpdate(lm)).getId();
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                    }
                }
            });

            realm.close();
        }

        return resultId;
    }

    @Override
    public long deleteItem(final long id) {
        Realm realm = Realm.getDefaultInstance();

        if (realm != null) {
            /*realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {


                }
            });*/

            RealmObject realmResults = realm.where(LinkModel.class).equalTo("id", id).findFirst();
            realm.beginTransaction();
            Objects.requireNonNull(realmResults).deleteFromRealm();
            realm.commitTransaction();
            EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));

            realm.close();
        }

        return id;
    }
}
