package com.buylist.solomakha.buylistapp.dagger.module;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.Database;
import com.buylist.solomakha.buylistapp.storage.db.RoomDatabaseImpl;

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
        return new Database();
    }

    @Provides
    @Singleton
    RoomDatabaseImpl provideRoomDatabase()
    {
        return RoomDatabaseImpl.getDatabase(context);
    }
}
