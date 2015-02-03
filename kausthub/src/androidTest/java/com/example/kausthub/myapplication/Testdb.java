package com.example.kausthub.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.kausthub.myapplication.data.WeatherDBhelper;

/**
 * Created by kausthub on 3/2/15.
 */
public class Testdb extends AndroidTestCase {
    public void testcreateDB() throws Throwable
    {
        mContext.deleteDatabase(WeatherDBhelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDBhelper( this.mContext).getWritableDatabase();
        assertEquals(true , db.isOpen());
        db.close();
    }
}
