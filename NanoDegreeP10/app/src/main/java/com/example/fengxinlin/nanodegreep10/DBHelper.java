package com.example.fengxinlin.nanodegreep10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by fengxinlin on 10/8/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InventoryBox";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBContract.Table1.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DBContract.Table1.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insert(Inventory item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, item.getProductName());
        values.put(DBContract.Table1.KEY_PRICE, item.getPrice());
        values.put(DBContract.Table1.KEY_QUANTITY, item.getQuantity());
        db.insert(DBContract.Table1.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Inventory> read() {
        ArrayList<Inventory> InventoryList = new ArrayList<Inventory>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+DBContract.Table1.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            //TODO:try dont use do while
            do {
                Inventory item = new Inventory();
                item.setId(Integer.parseInt(cursor.getString(0)));

                item.setProductName(cursor.getString(1));
                item.setQuantity(cursor.getInt(2));
                item.setPrice(cursor.getDouble(3));
                InventoryList.add(item);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return InventoryList;
    }

    public long update(double id, Inventory item) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, item.getProductName());
        values.put(DBContract.Table1.KEY_PRICE, item.getPrice());
        values.put(DBContract.Table1.KEY_QUANTITY, item.getQuantity());

        long i = db.update(DBContract.Table1.TABLE_NAME, values, DBContract.Table1.KEY_ID + "=?", new String[]{id + ""});

        return i;
    }


    public void delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.Table1.TABLE_NAME, "title=?", new String[]{name});
    }

    public int deleteAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBContract.Table1.TABLE_NAME, null, null);
    }

    public long rowCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db,DBContract.Table1.TABLE_NAME );
        db.close();
        return cnt;
    }
}
