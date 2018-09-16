package ben.com.linklauncher.data.db.realm;

import android.annotation.SuppressLint;
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

public class RealmDBHelper {

    //@SuppressLint("StaticFieldLeak")
    //private static Realm realm;

    private static LinkModel result;
    private static boolean status;
    private static long resultId;

    public static List<LinkModel> getLinksList() {

        Realm realm = Realm.getDefaultInstance();

        List<LinkModel> resList = new RealmList<>();

        if (realm != null) {

            RealmResults<LinkModel> results = realm.where(LinkModel.class).findAll();

            resList = realm.copyFromRealm(results);

        }
        return resList;
    }

    public static LinkModel getLink(long id) {

        Realm realm = Realm.getDefaultInstance();

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

    public static /*LinkModel*/ long addLink(final LinkModel model) {

        Realm realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            /*realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    if (model != null) {
                        LinkModel lm = realm.where(LinkModel.class).equalTo("id", model.getId()).findFirst();
                        if (lm != null) {
                            lm.setStatus(model.getStatus());
                            //realm.beginTransaction();
                            result = realm.copyFromRealm(realm.copyToRealmOrUpdate(lm));
                            realm.commitTransaction();
                            EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                        }else {
                            //realm.beginTransaction();
                            result = realm.copyFromRealm(realm.copyToRealm(model));
                            realm.commitTransaction();
                            EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                        }
                    }
                }
            });*/

            if (model != null) {
                LinkModel lm = realm.where(LinkModel.class).equalTo("id", model.getId()).findFirst();
                realm.beginTransaction();
                if (lm != null) {
                    lm.setStatus(model.getStatus());
                    resultId = /*realm.copyFromRealm(*/realm.copyToRealmOrUpdate(lm).getId()/*)*/;
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                }else {
                    resultId = /*realm.copyFromRealm(*/realm.copyToRealmOrUpdate(model).getId()/*)*/;
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                }
                realm.commitTransaction();
            }

            realm.close();
        }

        return resultId;
    }

    public static LinkModel updateLink(final LinkModel model) {

        Realm realm = Realm.getDefaultInstance();

        result = null;

        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    LinkModel lm = realm.where(LinkModel.class).equalTo("id", model.getId()).findFirst();

                    if (lm != null) {
                        lm.setStatus(model.getStatus());

                        result = realm.copyFromRealm(realm.copyToRealmOrUpdate(lm));
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
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
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATED_DB));
                }
            });

            realm.close();
        }

        return status;
    }
}
