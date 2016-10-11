package com.example.fengxinlin.nanodegreep9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fengxinlin on 10/7/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HabitTracker";
    Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBContract.Table.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        context.deleteDatabase(DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addHabit(HabitObject habitObject) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBContract.Table.KEY_HABIT, habitObject.getHabit());
        contentValues.put(DBContract.Table.KEY_FREQUENCY, habitObject.getFrequency());
        sqLiteDatabase.insert(DBContract.Table.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public Cursor getDetail(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] projection = {DBContract.Table.KEY_ID,
                DBContract.Table.KEY_HABIT, DBContract.Table.KEY_FREQUENCY};
        String selection = DBContract.Table.KEY_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};


        Cursor cursor = sqLiteDatabase.query(DBContract.Table.TABLE_NAME, projection, selection,
                selectionArgs, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public void updateHabitRow(double rowId, String newContent, String newContent1) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Table.KEY_HABIT, newContent);
        contentValues.put(DBContract.Table.KEY_FREQUENCY, newContent1);
        sqLiteDatabase.update(DBContract.Table.TABLE_NAME, contentValues, DBContract.Table.KEY_ID + "=" + rowId, null);
    }

    public void deleteHabitTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + DBContract.Table.TABLE_NAME);
    }
}
