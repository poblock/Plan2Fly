package pl.poblock.plan2fly.data.repository.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysztof.poblocki on 2017-01-26.
 */

public class MiastaLocalSource {
    private static MiastaLocalSource INSTANCE = null;
    private MiastaDbHelper dbHelper;
    private MiastaLocalSource(Context context) {
        dbHelper = new MiastaDbHelper(context);
    }
    public MiastaLocalSource getInstance(Context context) {
        if(INSTANCE==null) {
            INSTANCE = new MiastaLocalSource(context);
        }
        return INSTANCE;
    }

//    public void getTasks(LoadTasksCallback callback) {
//        List<Task> tasks = new ArrayList<Task>();
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        String[] projection = {
//                TaskEntry.COLUMN_NAME_ENTRY_ID,
//                TaskEntry.COLUMN_NAME_TITLE,
//                TaskEntry.COLUMN_NAME_DESCRIPTION,
//                TaskEntry.COLUMN_NAME_COMPLETED
//        };
//
//        Cursor c = db.query(
//                TaskEntry.TABLE_NAME, projection, null, null, null, null, null);
//
//        if (c != null && c.getCount() > 0) {
//            while (c.moveToNext()) {
//                String itemId = c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_ENTRY_ID));
//                String title = c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_TITLE));
//                String description =
//                        c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_DESCRIPTION));
//                boolean completed =
//                        c.getInt(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_COMPLETED)) == 1;
//                Task task = new Task(title, description, itemId, completed);
//                tasks.add(task);
//            }
//        }
//        if (c != null) {
//            c.close();
//        }
//
//        db.close();
//
//        if (tasks.isEmpty()) {
//            callback.onDataNotAvailable();
//        } else {
//            callback.onTasksLoaded(tasks);
//        }
//
//    }
}
