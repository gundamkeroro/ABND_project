package com.example.fengxinlin.nanodegreep9;

import android.provider.BaseColumns;

/**
 * Created by fengxinlin on 10/7/16.
 */
public final class DBContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private DBContract() {
        throw new AssertionError("No DBContract instances");
    }

    public static abstract class Table implements BaseColumns {
        public static final String TABLE_NAME = "habittable";
        public static final String KEY_ID = "id";
        public static final String KEY_HABIT = "habit";
        public static final String KEY_FREQUENCY = "frequency";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY UNIQUE ," +
                KEY_HABIT + TEXT_TYPE + COMMA_SEP +
                KEY_FREQUENCY + " INTEGER" +
                " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}