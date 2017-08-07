package com.buylist.solomakha.buylistapp.dagger.module;

import android.content.Context;

import com.buylist.solomakha.buylistapp.db.AppDatabase;
import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.dal.RoomDatabase;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DbModule
{
    Context context;

    public DbModule(Context context)
    {
        this.context = context;
    }


    @Provides
    @Singleton
    Storage provideStorage()
    {
        return new RoomDatabase();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase()
    {
        return AppDatabase.getDatabase(context);
    }
}
