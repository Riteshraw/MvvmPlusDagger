package com.rr.project.daggerplusarchitecture.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rr.project.daggerplusarchitecture.Utils.Constants;

@Database(entities = {User.class,UserAddress.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract AddressDao addressDao();

    private static AppDatabase DB_INSTANCE;

    public static AppDatabase getDbInstance(final Context context) {
        if (DB_INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (DB_INSTANCE == null) {
                    DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DB_NAME).build();
                }
            }
        }
        return DB_INSTANCE;
    }

}