package ben.com.linklauncher.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.Objects;

import javax.inject.Inject;

import ben.com.linklauncher.core.App;
import ben.com.linklauncher.data.db.realm.RealmRepository;
import ben.com.linklauncher.data.model.LinkModel;
import ben.com.linklauncher.util.LinkUtil;

public class LinkContentProvider extends ContentProvider {

    public static final String AUTHORITY = "ben.com.linklauncher.linkprovider";
    public static final String LINK_PATH = "link";

    static final int LINKS_URI = 100;
    static final int LINKS_URI_ID = 101;

    private static final String[] columns = {"id", "link", "date", "status"};

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, LINK_PATH, LINKS_URI);
        uriMatcher.addURI(AUTHORITY, LINK_PATH + "/#", LINKS_URI_ID);
    }

    @Inject
    RealmRepository<LinkModel> repository;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        //int id = 0;

        String uriId = uri.getLastPathSegment();

        /*switch (uriMatcher.match(uri)) {
            case LINKS_URI:

                break;
            case LINKS_URI_ID:
                String uriId = uri.getLastPathSegment();
                id = (int) repository.deleteItem(Long.parseLong(uriId));
                break;
        }*/

        return (int) repository.deleteItem(Long.parseLong(uriId));
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (repository == null) {
            lazyInject();
        }

        long id = repository.addItem(LinkUtil.modelFromContentValues(values));

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        if (repository == null) {
            lazyInject();
        }

        MatrixCursor cursor = new MatrixCursor(columns);

        switch (uriMatcher.match(uri)) {
            case LINKS_URI:
                for (LinkModel elem : repository.getItemsList()) {
                    Object[] rowData = new Object[] {elem.getId(), elem.getLink(), elem.getDate(), elem.getStatus()};
                    cursor.addRow(rowData);
                }
                break;
            case LINKS_URI_ID:
                LinkModel model = repository.getItem(Long.parseLong(uri.getLastPathSegment()));
                Object[] rowData = new Object[] {model.getId(), model.getLink(), model.getDate(), model.getStatus()};
                cursor.addRow(rowData);
                break;
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        if (repository == null) {
            lazyInject();
        }

        long id = repository.updateItem(LinkUtil.modelFromContentValues(values));

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return (int) id;
    }

    private void lazyInject() {
        App.getAppInjector().inject(this);
    }
}
