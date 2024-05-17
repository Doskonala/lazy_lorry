package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "LazyLorry.db";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_LORRY_NAME = "Lorries";
    private static final String COLUMN_CAR_NUMBER = "car_number";
    private static final String COLUMN_LOAD_CAPACITY = "load_capacity";
    private static final String COLUMN_MANUFACTURER = "manufacturer";
    private static final String COLUMN_TYPE_OF_FREIGHT = "type_of_freight";
    private static final String COLUMN_BODYWORK = "bodywork";
    private static final String COLUMN_PRODUCTION_YEAR = "production_year";
    private static final String TABLE_DRIVER_NAME = "Drivers";
    private static final String COLUMN_DRIVER_FULL_NAME = "full_name";
    private static final String COLUMN_DRIVER_EMAIL = "email";
    private static final String COLUMN_DRIVER_PHONE = "phone";
    private static final String COLUMN_DRIVER_ADDITIONAL_CONTACT_INFO = "additional_contact_info";
    private static final String COLUMN_LICENSE_NUMBER = "license_number";
    private static final String COLUMN_LICENSE_SERIES = "license_series";
    private static final String COLUMN_LICENSE_CATEGORY = "license_category";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_HIRE_DATE = "hire_date";
    private static final String TABLE_PARTNER_NAME = "Partners";
    private static final String COLUMN_PARTNER_ID = "_id";
    private static final String COLUMN_PARTNER_FULL_NAME = "full_name";
    private static final String COLUMN_PARTNER_EMAIL = "email";
    private static final String COLUMN_PARTNER_PHONE = "phone";
    private static final String COLUMN_PARTNER_ADDITIONAL_CONTACT_INFO = "additional_contact_info";
    private static final String COLUMN_PARTNER_COUNTRY = "country";
    private static final String COLUMN_PARTNER_COMPANY = "company";
    private static final String COLUMN_PARTNER_WEBSITE = "website";
    private static final String TABLE_CLIENT_NAME = "Clients";
    private static final String COLUMN_CLIENT_ID = "_id";
    private static final String COLUMN_CLIENT_FULL_NAME = "full_name";
    private static final String COLUMN_CLIENT_EMAIL = "email";
    private static final String COLUMN_CLIENT_PHONE = "phone";
    private static final String COLUMN_CLIENT_ADDITIONAL_CONTACT_INFO = "additional_contact_info";
    private static final String COLUMN_CLIENT_COMPANY = "company";
    private static final String COLUMN_CLIENT_WEBSITE = "website";
    private static final String TABLE_ORDER_NAME = "Orders";
    private static final String COLUMN_ORDER_ID = "_id";
    private static final String COLUMN_ORDER_GROUP = "freight_group";
    private static final String COLUMN_ORDER_TYPE = "type";
    private static final String COLUMN_ORDER_TRANSPORTATION = "way_of_transportation";
    private static final String COLUMN_ORDER_CLIENT_ID = "client_id";
    private static final String COLUMN_ORDER_VALUE = "declared_value";
    private static final String COLUMN_ORDER_PARTNER_ID = "partner_id";
    private static final String COLUMN_ORDER_IS_DELIVERED = "is_delivered";
    private static final String TABLE_TRIP_NAME = "Trips";
    private static final String COLUMN_TRIP_ID = "_id";
    private static final String COLUMN_TRIP_ORDER_ID = "order_id";
    private static final String COLUMN_TRIP_CAR_NUMBER = "car_number";
    private static final String COLUMN_TRIP_DRIVER_NUMBER = "driver_number";
    private static final String COLUMN_TRIP_ORIGIN = "origin";
    private static final String COLUMN_TRIP_DESTINATION = "destination";
    private static final String COLUMN_TRIP_DISTANCE = "distance";
    private static final String COLUMN_TRIP_DATE = "date";
    private static final String COLUMN_TRIP_TIME = "time";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryLorry = "CREATE TABLE " + TABLE_LORRY_NAME + " (" + COLUMN_CAR_NUMBER + " VARCHAR(8) NOT NULL PRIMARY KEY, "
                + COLUMN_LOAD_CAPACITY + " DOUBLE CHECK( "+ COLUMN_LOAD_CAPACITY +" > 0) NOT NULL, " + COLUMN_MANUFACTURER + " VARCHAR(100), " + COLUMN_TYPE_OF_FREIGHT
                + " VARCHAR(21) CHECK( " + COLUMN_TYPE_OF_FREIGHT
                + " IN ('швидкопсувний', 'наливний', 'пилоподібний', 'газоподібний', 'навалочний і насипний', 'небезпечний', 'збірний', 'живий') ) NOT NULL DEFAULT 'збірний', "
                + COLUMN_BODYWORK + " VARCHAR(17) CHECK( " + COLUMN_BODYWORK
                + " IN ('Самоскиди', 'Бортові', 'Криті', 'З тентом', 'Автоцистерни', 'Бетономішалки', 'Авторефрижератори', 'Автовози', 'Контейнерні', 'Тягачі') ) NOT NULL DEFAULT 'Криті', "
                + COLUMN_PRODUCTION_YEAR + " INTEGER CHECK( " + COLUMN_PRODUCTION_YEAR +" > 1896) NOT NULL); ";
        String queryDriver = "CREATE TABLE " + TABLE_DRIVER_NAME + " (" + COLUMN_DRIVER_FULL_NAME + " VARCHAR(100) NOT NULL, "
                + COLUMN_DRIVER_EMAIL + " VARCHAR(50) NOT NULL, " + COLUMN_DRIVER_PHONE + " VARCHAR(13) NOT NULL, "
                + COLUMN_DRIVER_ADDITIONAL_CONTACT_INFO + " VARCHAR(100), "
                + COLUMN_LICENSE_NUMBER + " VARCHAR(6) NOT NULL PRIMARY KEY, " + COLUMN_LICENSE_SERIES + " VARCHAR(3) NOT NULL, "
                + COLUMN_LICENSE_CATEGORY + " VARCHAR(3) CHECK( "
                + COLUMN_LICENSE_CATEGORY + " IN ( 'C1', 'C', 'C1E', 'CE')) NOT NULL, "
                + COLUMN_SALARY + " DOUBLE CHECK( " + COLUMN_SALARY + " >= 8000) NOT NULL, "
                + COLUMN_HIRE_DATE + " DATE NOT NULL);";
        String queryPartner = "CREATE TABLE " + TABLE_PARTNER_NAME + " (" + COLUMN_PARTNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PARTNER_FULL_NAME + " VARCHAR(100) NOT NULL, "
                + COLUMN_PARTNER_EMAIL + " VARCHAR(50) NOT NULL, " + COLUMN_PARTNER_PHONE + " VARCHAR(13) NOT NULL, "
                + COLUMN_PARTNER_ADDITIONAL_CONTACT_INFO + " VARCHAR(100), "
                + COLUMN_PARTNER_COUNTRY + " VARCHAR(60) NOT NULL, "
                + COLUMN_PARTNER_COMPANY + " VARCHAR(60) NOT NULL, "
                + COLUMN_PARTNER_WEBSITE + " VARCHAR(60));";
        String queryClient = "CREATE TABLE " + TABLE_CLIENT_NAME + " (" + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CLIENT_FULL_NAME + " VARCHAR(100) NOT NULL, "
                + COLUMN_CLIENT_EMAIL + " VARCHAR(50) NOT NULL, " + COLUMN_CLIENT_PHONE + " VARCHAR(13) NOT NULL, "
                + COLUMN_CLIENT_ADDITIONAL_CONTACT_INFO + " VARCHAR(100), "
                + COLUMN_CLIENT_COMPANY + " VARCHAR(60), "
                + COLUMN_CLIENT_WEBSITE + " VARCHAR(60));";
        String queryOrder = "CREATE TABLE " + TABLE_ORDER_NAME + " (" + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORDER_GROUP + " VARCHAR(9) CHECK( " + COLUMN_ORDER_GROUP + " IN ('товарна', 'нетоварна')) NOT NULL, "
                + COLUMN_ORDER_TYPE + " VARCHAR(21) CHECK( " + COLUMN_ORDER_TYPE
                + " IN ('швидкопсувний', 'наливний', 'пилоподібний', 'газоподібний', 'навалочний і насипний', 'небезпечний', 'збірний', 'живий') ) NOT NULL, "
                + COLUMN_ORDER_TRANSPORTATION + " VARCHAR(12) CHECK( " + COLUMN_ORDER_TRANSPORTATION + " IN ('генеральний', 'негабаритний') ) NOT NULL,"
                + COLUMN_ORDER_CLIENT_ID + " INTEGER NOT NULL, "
                + COLUMN_ORDER_VALUE + " DOUBLE CHECK( " + COLUMN_ORDER_VALUE + " > 0) NOT NULL, "
                + COLUMN_ORDER_PARTNER_ID + " INTEGER, "
                + COLUMN_ORDER_IS_DELIVERED + " BOOLEAN CHECK( " + COLUMN_ORDER_IS_DELIVERED + " IN (0, 1)), FOREIGN KEY("
                + COLUMN_ORDER_CLIENT_ID + ") REFERENCES " + TABLE_CLIENT_NAME + "(" + COLUMN_CLIENT_ID +") ON DELETE CASCADE, FOREIGN KEY("
                + COLUMN_ORDER_PARTNER_ID + ") REFERENCES " + TABLE_PARTNER_NAME + "(" + COLUMN_PARTNER_ID +") ON DELETE CASCADE);";
        String queryTrip = "CREATE TABLE " + TABLE_TRIP_NAME + " (" + COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TRIP_ORDER_ID + " INTEGER NOT NULL, " + COLUMN_TRIP_CAR_NUMBER + " VARCHAR(8) NOT NULL, "
                + COLUMN_TRIP_DRIVER_NUMBER + " VARCHAR(6) NOT NULL, " + COLUMN_TRIP_ORIGIN + " VARCHAR(100) NOT NULL, "
                + COLUMN_TRIP_DESTINATION + " VARCHAR(100) NOT NULL, "
                + COLUMN_TRIP_DISTANCE + " DOUBLE CHECK( " + COLUMN_TRIP_DISTANCE + " > 0) NOT NULL, "
                + COLUMN_TRIP_DATE + " DATE NOT NULL, " + COLUMN_TRIP_TIME + " TIME NOT NULL, FOREIGN KEY("
                + COLUMN_TRIP_ORDER_ID + ") REFERENCES " + TABLE_ORDER_NAME + "(" + COLUMN_ORDER_ID + ") ON DELETE CASCADE, FOREIGN KEY("
                + COLUMN_TRIP_CAR_NUMBER + ") REFERENCES " + TABLE_LORRY_NAME + "(" + COLUMN_CAR_NUMBER + ") ON DELETE CASCADE, FOREIGN KEY("
                + COLUMN_TRIP_DRIVER_NUMBER + ") REFERENCES " + TABLE_DRIVER_NAME + "(" + COLUMN_LICENSE_NUMBER + ") ON DELETE CASCADE);";
        db.execSQL(queryLorry);
        db.execSQL(queryDriver);
        db.execSQL(queryPartner);
        db.execSQL(queryClient);
        db.execSQL(queryOrder);
        db.execSQL(queryTrip);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryLorry = "DROP TABLE IF EXISTS " + TABLE_LORRY_NAME + "; ";
        String queryDriver = "DROP TABLE IF EXISTS " + TABLE_DRIVER_NAME + "; ";
        String queryPartner = "DROP TABLE IF EXISTS " + TABLE_PARTNER_NAME + "; ";
        String queryClient = "DROP TABLE IF EXISTS " + TABLE_CLIENT_NAME + "; ";
        String queryOrder = "DROP TABLE IF EXISTS " + TABLE_ORDER_NAME + "; ";
        String queryTrip = "DROP TABLE IF EXISTS " + TABLE_TRIP_NAME + "; ";
        db.execSQL(queryLorry);
        db.execSQL(queryDriver);
        db.execSQL(queryPartner);
        db.execSQL(queryClient);
        db.execSQL(queryOrder);
        db.execSQL(queryTrip);
        onCreate(db);
    }

    void addLorry (String car_number, double load_capacity, String manufacturer, String type_of_freight, String bodywork, int production_year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CAR_NUMBER, car_number);
        cv.put(COLUMN_LOAD_CAPACITY, load_capacity);
        cv.put(COLUMN_MANUFACTURER, manufacturer);
        cv.put(COLUMN_TYPE_OF_FREIGHT, type_of_freight);
        cv.put(COLUMN_BODYWORK, bodywork);
        cv.put(COLUMN_PRODUCTION_YEAR, production_year);
        long result = db.insert(TABLE_LORRY_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про вантажівку не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про вантажівку збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllLorries () {
        String query = "SELECT * FROM " + TABLE_LORRY_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void addDriver (String full_name, String email, String phone, String additional_contact_info, String license_number, String license_series, String license_category, double salary, String hire_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DRIVER_FULL_NAME, full_name);
        cv.put(COLUMN_DRIVER_EMAIL, email);
        cv.put(COLUMN_DRIVER_PHONE, phone);
        cv.put(COLUMN_DRIVER_ADDITIONAL_CONTACT_INFO, additional_contact_info);
        cv.put(COLUMN_LICENSE_NUMBER, license_number);
        cv.put(COLUMN_LICENSE_SERIES, license_series);
        cv.put(COLUMN_LICENSE_CATEGORY, license_category);
        cv.put(COLUMN_SALARY, salary);
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = formatter.parse(hire_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        cv.put(COLUMN_HIRE_DATE, dateFormat.format(date));
        long result = db.insert(TABLE_DRIVER_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про водія не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про водія збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDrivers () {
        String query = "SELECT * FROM " + TABLE_DRIVER_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void addPartner (String full_name, String email, String phone, String additional_contact_info, String country, String company, String website){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PARTNER_FULL_NAME, full_name);
        cv.put(COLUMN_PARTNER_EMAIL, email);
        cv.put(COLUMN_DRIVER_PHONE, phone);
        cv.put(COLUMN_PARTNER_ADDITIONAL_CONTACT_INFO, additional_contact_info);
        cv.put(COLUMN_PARTNER_COUNTRY, country);
        cv.put(COLUMN_PARTNER_COMPANY, company);
        cv.put(COLUMN_PARTNER_WEBSITE, website);
        long result = db.insert(TABLE_PARTNER_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про партнера не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про партнера збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllPartners () {
        String query = "SELECT * FROM " + TABLE_PARTNER_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void addClient (String full_name, String email, String phone, String additional_contact_info, String company, String website){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CLIENT_FULL_NAME, full_name);
        cv.put(COLUMN_CLIENT_EMAIL, email);
        cv.put(COLUMN_CLIENT_PHONE, phone);
        cv.put(COLUMN_CLIENT_ADDITIONAL_CONTACT_INFO, additional_contact_info);
        cv.put(COLUMN_CLIENT_COMPANY, company);
        cv.put(COLUMN_CLIENT_WEBSITE, website);
        long result = db.insert(TABLE_CLIENT_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про клієнта не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про клієнта збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllClients () {
        String query = "SELECT * FROM " + TABLE_CLIENT_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void addOrder (String group, String type, String transportation, int client, double value, int partner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ORDER_GROUP, group);
        cv.put(COLUMN_ORDER_TYPE, type);
        cv.put(COLUMN_ORDER_TRANSPORTATION, transportation);
        cv.put(COLUMN_ORDER_CLIENT_ID, client);
        cv.put(COLUMN_ORDER_VALUE, value);
        cv.put(COLUMN_ORDER_PARTNER_ID, partner);
        long result = db.insert(TABLE_ORDER_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про замовлення не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про замовлення збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllOrders () {
        String query = "SELECT " + TABLE_ORDER_NAME + "." + COLUMN_ORDER_ID + ", " + COLUMN_ORDER_GROUP + ", " + COLUMN_ORDER_TYPE + ", "
                + COLUMN_ORDER_TRANSPORTATION + ", " + TABLE_CLIENT_NAME + "." + COLUMN_CLIENT_ID + ", "
                + TABLE_CLIENT_NAME + "." + COLUMN_CLIENT_FULL_NAME + ", " + COLUMN_ORDER_VALUE + ", "
                + TABLE_PARTNER_NAME + "." + COLUMN_PARTNER_ID + ", " + TABLE_PARTNER_NAME + "." + COLUMN_PARTNER_FULL_NAME + ", "
                + TABLE_PARTNER_NAME + "." + COLUMN_PARTNER_COMPANY
                + " FROM " + TABLE_ORDER_NAME
                + " LEFT JOIN " + TABLE_CLIENT_NAME + " ON " + TABLE_CLIENT_NAME + "." + COLUMN_CLIENT_ID + " = "
                + TABLE_ORDER_NAME + "." + COLUMN_ORDER_CLIENT_ID
                + " LEFT JOIN " + TABLE_PARTNER_NAME + " ON " + TABLE_PARTNER_NAME + "." + COLUMN_PARTNER_ID + " = "
                + TABLE_ORDER_NAME + "." + COLUMN_ORDER_PARTNER_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void addTrip (int order, String lorry, String driver, String origin, String destination, double distance, String start_date, String start_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TRIP_ORDER_ID, order);
        cv.put(COLUMN_TRIP_CAR_NUMBER, lorry);
        cv.put(COLUMN_TRIP_DRIVER_NUMBER, driver);
        cv.put(COLUMN_TRIP_ORIGIN, origin);
        cv.put(COLUMN_TRIP_DESTINATION, destination);
        cv.put(COLUMN_TRIP_DISTANCE, distance);
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = formatter.parse(start_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        cv.put(COLUMN_TRIP_DATE, dateFormat.format(date));
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.parse(start_time);
        }
        cv.put(COLUMN_TRIP_TIME, String.valueOf(time));
        long result = db.insert(TABLE_TRIP_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Дані про перевезення не були збережені", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Дані про перевезення збережено успішно", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllTrips () {
        String query = "SELECT " + COLUMN_TRIP_ID + ", " + COLUMN_TRIP_ORDER_ID + ", " + COLUMN_TRIP_CAR_NUMBER + ", "
                + COLUMN_TRIP_DRIVER_NUMBER + ", " + TABLE_DRIVER_NAME + "." + COLUMN_DRIVER_FULL_NAME + ", "
                + COLUMN_TRIP_ORIGIN + ", " + COLUMN_TRIP_DESTINATION + ", " + COLUMN_TRIP_DISTANCE + ", "
                + COLUMN_TRIP_DATE + ", " + COLUMN_TRIP_TIME
                + " FROM " + TABLE_TRIP_NAME + " LEFT JOIN " + TABLE_DRIVER_NAME
                + " ON " + TABLE_TRIP_NAME + "." + COLUMN_TRIP_DRIVER_NUMBER + " = "
                + TABLE_DRIVER_NAME + "." + COLUMN_LICENSE_NUMBER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
