package ben.com.linklauncher.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.db.Repository;
import ben.com.linklauncher.data.db.realm.RealmDBHelper;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmQuery;

public class LinkContentProvider extends ContentProvider {

    public static final String AUTHORITY = "ben.com.linklauncher.linkprovider";
    /*public static final Uri URI_LINK =  Uri.parse(
            "content://" + AUTHORITY + "/" + Cheese.TABLE_NAME);*/

    private static final String[] columns = {"id", "link", "date", "status"};

    public LinkContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        LinkModel model = RealmDBHelper.addLink(LinkUtil.modelFromContentValues(values));

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, model.getId());
    }

    @Override
    public boolean onCreate() {

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        MatrixCursor cursor = new MatrixCursor(columns);

        for (LinkModel elem : RealmDBHelper.getLinksList()) {
            Object[] rowData = new Object[] {elem.getId(), elem.getLink(), elem.getDate(), elem.getStatus()};
            cursor.addRow(rowData);
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
