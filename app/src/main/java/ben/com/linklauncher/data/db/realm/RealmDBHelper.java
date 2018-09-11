package ben.com.linklauncher.data.db.realm;

import android.annotation.SuppressLint;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmDBHelper {

    @SuppressLint("StaticFieldLeak")
    private static Realm realm;

    /*public static Word addWord(final Word item) {
        wordRealm = Realm.getDefaultInstance();

        if (wordRealm != null) {
            wordRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    List<Translate> savedTranslates = new RealmList<>();

                    if (item.getTranslates() != null && item.getTranslates().size() > 0) {
                        for (Translate elem : item.getTranslates()) {
                            elem.setId(KeyGenerator.generateKey(item.getValue(), elem.getValue()));
                            savedTranslates.add(realm.copyToRealmOrUpdate(elem));
                        }
                    }

                    if (item != null && !item.getValue().isEmpty() && !item.getPartsOfSpeech().isEmpty()) {

                        long id = KeyGenerator.generateKey(item.getPartsOfSpeech(), item.getValue());

                        Word w = wordRealm.where(Word.class).equalTo("id", id).findFirst();

                        if (w != null) {
                            for (Translate elem : savedTranslates) {
                                w.addTranslate(elem);
                            }
                            wordRes = realm.copyToRealmOrUpdate(w);
                        }else {
                            item.setId(KeyGenerator.generateKey(item.getPartsOfSpeech(), item.getValue()));
                            wordRes = realm.copyToRealmOrUpdate(item);
                        }
                    }
                }
            });

            wordRealm.close();
        }

        return wordRes;
    }

    public static Word getWord(long id) {
        wordRealm = Realm.getDefaultInstance();

        if (wordRealm != null) {
            wordRes = wordRealm.where(Word.class).equalTo("id", id).findFirst();

            wordRealm.close();
        }

        return wordRes;
    }

    public static List<Word> getWordsList() {
        wordRealm = Realm.getDefaultInstance();
        List<Word> resList = new RealmList<>();

        if (wordRealm != null) {

            RealmResults<Word> results = wordRealm.where(Word.class).findAll();

            resList = wordRealm.copyFromRealm(results);

            //wordRealm.close();
        }
        return resList;
    }*/
}
