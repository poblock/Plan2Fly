package pl.poblock.plan2fly.data.repository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by krzysztof.poblocki on 2017-01-26.
 */

public class MiastaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Miasta.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static abstract class MiastoEntry implements BaseColumns {
        public static final String TABLE_NAME = "miasta";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MiastoEntry.TABLE_NAME + " (" +
                    MiastoEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    MiastoEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    MiastoEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    MiastoEntry.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                    MiastoEntry.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    MiastoEntry.COLUMN_NAME_LATITUDE + TEXT_TYPE + COMMA_SEP +
                    MiastoEntry.COLUMN_NAME_LONGITUDE + TEXT_TYPE +
                    " )";

    public static String[] selectAll  = {
            MiastoEntry.COLUMN_NAME_ID,
            MiastoEntry.COLUMN_NAME_NAME,
            MiastoEntry.COLUMN_NAME_CODE,
            MiastoEntry.COLUMN_NAME_COUNTRY,
            MiastoEntry.COLUMN_NAME_LATITUDE,
            MiastoEntry.COLUMN_NAME_LONGITUDE
    };

    public MiastaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
