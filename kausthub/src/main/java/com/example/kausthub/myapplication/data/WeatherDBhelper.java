package com.example.kausthub.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by kausthub on 1/2/15.
 */
public class WeatherDBhelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="Weather.db";
    private static final int DATABASE_VERION= 1;
    private SQLiteDatabase db;

    public WeatherDBhelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String create_location_table ="CREATE TABLE" + WeatherContract.LocationEntry.TABLE_NAME +"(" +
                WeatherContract.LocationEntry._ID + "INTEGER PRIMARY KEY ," +
                WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + "TEXT UNIQUE NOT NULL ," +
                WeatherContract.LocationEntry.COLUMN_CITY_NAME + "TEXT NOT NULL ," +
                WeatherContract.LocationEntry.COLUMN_COORD_LAT + "TEXT NOT NULL ,"+
                WeatherContract.LocationEntry.COLUMN_COORD_LONG + "TEXT NOT NULL ," +
                "UNIQUE (" + WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + ") ON CONFLICT IGNORE" + ");";


       final String create_weather_table = "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME+"("+
               WeatherContract.WeatherEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT ," +
               WeatherContract.WeatherEntry.COLUMN_LOC_KEY + "INTEGER NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_DATETEXT + "TEXT NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_SHORT_DESC+"TEXT NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_WEATHER_ID + "INTEGER NOT NULL,"+
               WeatherContract.WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_HUMIDITY + "REAL NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_PRESSURE + "REAL NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_WIND_SPEED + "REAL NOT NULL," +
               WeatherContract.WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +

               "FOREIGN KEY (" + WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
               WeatherContract.LocationEntry.TABLE_NAME +"(" + WeatherContract.LocationEntry._ID + ")," +
               "UNIQUE (" + WeatherContract.WeatherEntry.COLUMN_DATETEXT + "," + WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE );";
        db.execSQL(create_location_table);
        db.execSQL(create_weather_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS" + WeatherContract.LocationEntry.TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS" + WeatherContract.WeatherEntry.TABLE_NAME);
    onCreate(db);
    }
}
