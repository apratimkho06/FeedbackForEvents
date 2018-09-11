package com.example.feedback.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "feedbackdb";
    private static final int DATABASE_VERSION = 1;
    private Dao<Event, Long> eventDao;
    private Dao<Responses, Integer> responseDao;

    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static DatabaseHelper helper = null;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Event.class);
            TableUtils.createTable(connectionSource,Responses.class);
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Event.class,false);
            TableUtils.dropTable(connectionSource,Responses.class,false);
            onCreate(database,connectionSource);
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<Event, Long> getEventDao() throws SQLException, java.sql.SQLException {
        if(eventDao == null)
            eventDao = getDao(Event.class);
        return eventDao;
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return helper;
    }

    public Dao<Responses, Integer> getResponseDao() throws SQLException, java.sql.SQLException {
        if(responseDao == null)
            responseDao = getDao(Responses.class);
        return responseDao;
    }

    @Override
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            responseDao = null;
            eventDao = null;
            helper = null;
        }
    }
}
