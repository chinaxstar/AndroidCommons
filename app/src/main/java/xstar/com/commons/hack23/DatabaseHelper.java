package xstar.com.commons.hack23;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author: xstar
 * since: 2017-05-02.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "hack23.db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + TodoContentProvider.TODO_TABLE_NAME + " ("
                + TodoContentProvider.COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TodoContentProvider.COLUMN_SERVER_ID + " INTEGER,"
                + TodoContentProvider.COLUMN_TITLE + " LONGTEXT,"
                + TodoContentProvider.COLUMN_STATUS_FLAG + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TodoContentProvider.TODO_TABLE_NAME);
        onCreate(db);
    }
}
