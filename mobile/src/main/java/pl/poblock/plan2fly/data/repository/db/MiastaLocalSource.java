package pl.poblock.plan2fly.data.repository.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;

/**
 * Created by krzysztof.poblocki on 2017-01-26.
 */

public class MiastaLocalSource {
    private static MiastaLocalSource INSTANCE = null;
    private MiastaDbHelper mDbHelper;
    private MiastaLocalSource(Context context) {
        mDbHelper = new MiastaDbHelper(context);
    }
    public static MiastaLocalSource getInstance(Context context) {
        if(INSTANCE==null) {
            INSTANCE = new MiastaLocalSource(context);
        }
        return INSTANCE;
    }

    public List<Miasto> getMiasta() {
        List<Miasto> miasta = new ArrayList<Miasto>();
        try {
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            Cursor c = db.query(MiastaDbHelper.MiastoEntry.TABLE_NAME, MiastaDbHelper.selectAll, null, null, null, null, null);
            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    String name = c.getString(c.getColumnIndexOrThrow(MiastaDbHelper.MiastoEntry.COLUMN_NAME_NAME));
                    String code = c.getString(c.getColumnIndexOrThrow(MiastaDbHelper.MiastoEntry.COLUMN_NAME_CODE));
                    String country = c.getString(c.getColumnIndexOrThrow(MiastaDbHelper.MiastoEntry.COLUMN_NAME_COUNTRY));
                    String latitude = c.getString(c.getColumnIndexOrThrow(MiastaDbHelper.MiastoEntry.COLUMN_NAME_LATITUDE));
                    String longitude = c.getString(c.getColumnIndexOrThrow(MiastaDbHelper.MiastoEntry.COLUMN_NAME_LONGITUDE));
                    Miasto miasto = new Miasto(name, code, country, latitude, longitude);
                    miasta.add(miasto);
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (IllegalStateException e) {
            // Send to analytics, log etc
        }
        return miasta;
    }

    public void saveMiasto(Miasto miasto) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_ID, miasto.getCode());
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_NAME, miasto.getName());
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_CODE, miasto.getCode());
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_COUNTRY, miasto.getCountry());
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_LATITUDE, miasto.getLatitude());
        values.put(MiastaDbHelper.MiastoEntry.COLUMN_NAME_LONGITUDE, miasto.getLongitude());
        db.insert(MiastaDbHelper.MiastoEntry.TABLE_NAME, null, values);
        db.close();
    }
}
