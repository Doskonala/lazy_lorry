package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "LazyLorry.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE1_NAME = "Lorry";
    private static final String COLUMN_CAR_NUMBER = "car_number";
    private static final String COLUMN_LOAD_CAPACITY = "load_capacity";
    private static final String COLUMN_MANUFACTURER = "manufacturer";
    private static final String COLUMN_TYPE_OF_FREIGHT = "type_of_freight";
    private static final String COLUMN_BODYWORK = "bodywork";
    private static final String COLUMN_PRODUCTION_YEAR = "production_year";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE1_NAME + " (" + COLUMN_CAR_NUMBER + " VARCHAR(8) PRIMARY KEY, "
                + COLUMN_LOAD_CAPACITY + " DOUBLE, " + COLUMN_MANUFACTURER + " VARCHAR(100), " + COLUMN_TYPE_OF_FREIGHT
                + " VARCHAR(21) CHECK( " + COLUMN_TYPE_OF_FREIGHT
                + " IN ('швидкопсувний', 'наливний', 'пилоподібний', 'газоподібний', 'навалочний і насипний', 'небезпечний', 'збірний', 'живий') ) NOT NULL DEFAULT 'збірний', "
                + COLUMN_BODYWORK + " VARCHAR(17) CHECK( " + COLUMN_BODYWORK
                + " IN ('Самоскиди', 'Бортові', 'Криті', 'З тентом', 'Автоцистерни', 'Бетономішалки', 'Авторефрижератори', 'Автовози', 'Контейнерні', 'Тягачі') ) NOT NULL DEFAULT 'Криті', "
                + COLUMN_PRODUCTION_YEAR + " INTEGER CHECK( " + COLUMN_PRODUCTION_YEAR +" > 1896) );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(db);
    }
}
