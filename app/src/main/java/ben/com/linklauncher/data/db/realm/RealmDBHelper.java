package ben.com.linklauncher.data.db.realm;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import ben.com.linklauncher.data.model.LinkModel;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmDBHelper {

    @SuppressLint("StaticFieldLeak")
    private static Realm realm;

    private static LinkModel result;
    private static boolean status;

    public static List<LinkModel> getLinksList() {

        realm = Realm.getDefaultInstance();

        List<LinkModel> resList = new RealmList<>();

        if (realm != null) {

            RealmResults<LinkModel> results = realm.where(LinkModel.class).findAll();

            resList = realm.copyFromRealm(results);

        }
        return resList;
    }

    public static LinkModel getLink(long id) {

        realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            //result = realm.where(LinkModel.class).equalTo("id", id).findFirst();
            LinkModel model = realm.where(LinkModel.class).equalTo("id", id).findFirst();
            if (model != null) {
                result = realm.copyFromRealm(model);
            }

            realm.close();
        }

        return result;
    }

    public static LinkModel addLink(final LinkModel model) {

        realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    if (model != null) {
                        result = realm.copyFromRealm(realm.copyToRealmOrUpdate(model));
                    }
                }
            });

            realm.close();
        }

        return result;
    }

    public static LinkModel updateLink(final LinkModel model) {

        realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    LinkModel lm = realm.where(LinkModel.class).equalTo("id", model.getId()).findFirst();

                    if (lm != null) {
                        lm.setStatus(model.getStatus());

                        result = realm.copyFromRealm(realm.copyToRealmOrUpdate(lm));
                    }
                }
            });

            realm.close();
        }

        return result;
    }

    public static boolean deleteLink(final LinkModel model) {

        Realm realm = Realm.getDefaultInstance();

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    RealmResults<LinkModel> realmResults = realm.where(LinkModel.class).equalTo("id", model.getId()).findAll();
                    status = realmResults.deleteAllFromRealm();
                }
            });

            realm.close();
        }

        return status;
    }
}
