package ben.com.linklauncher.util;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.model.LinkModel;

public class LinkUtil {

    public static final String ID = "id";
    public static final String LINK = "link";
    public static final String DATE = "date";
    public static final String STATUS = "status";

    public static Intent getRequest(LinkModel model, String action) {
        if (model != null) {
            Intent intent = new Intent(action);
            intent.putExtra(ID, model.getId());
            intent.putExtra(LINK, model.getLink());
            intent.putExtra(DATE, model.getDate());
            intent.putExtra(STATUS, model.getStatus());
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            return intent;
        }

        return null;
    }

    public static LinkModel getResponse(Intent intent) {
        LinkModel model = new LinkModel();
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(App.SHOW_LINK)) {

                model.setId(intent.getLongExtra(ID, 0L));
                model.setLink(intent.getStringExtra(LINK));
                model.setDate(intent.getStringExtra(DATE));
                model.setStatus(intent.getIntExtra(STATUS, 0));

                return model;
            }
        }

        return null;
    }

    public static ContentValues modelToContentValues(LinkModel model) {

        ContentValues cv = new ContentValues();

        cv.put(ID, model.getId());
        cv.put(LINK, model.getLink());
        cv.put(DATE, model.getDate());
        cv.put(STATUS, model.getStatus());

        return cv;
    }

    public static LinkModel modelFromContentValues(ContentValues cv) {

        LinkModel model = new LinkModel();

        model.setId(cv.getAsLong(ID));
        model.setLink(cv.getAsString(LINK));
        model.setDate(cv.getAsString(DATE));
        model.setStatus(cv.getAsInteger(STATUS));

        return model;
    }

    public static LinkModel modelFromCursor(MatrixCursor cursor) {
        final String[] columns = {"id", "link", "date", "status"};
        LinkModel model = new LinkModel();
        cursor.moveToFirst();
        return model;
    }
}
