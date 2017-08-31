package com.buylist.solomakha.buylistapp.dagger.module;

import android.content.Context;


import com.buylist.solomakha.buylistapp.mvp.models.BasketModel;
import com.buylist.solomakha.buylistapp.mvp.models.impl.BasketModelImpl;
import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.Database;
import com.buylist.solomakha.buylistapp.storage.db.RoomDatabaseImpl;
import com.buylist.solomakha.buylistapp.storage.db.TestRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class TestDbModule
{
    Context context;

    public TestDbModule(Context context)
    {
        this.context = context;
    }

    @Provides
    @Singleton
    Storage provideTestStorage()
    {
        return new Database();
    }

    @Provides
    @Singleton
    RoomDatabaseImpl provideTestRoomDatabase()
    {
        return TestRoomDatabase.getDatabase(context);
    }

    @Provides
    @Singleton
    BasketModel provideTestBasketModel()
    {
        return new BasketModelImpl();
    }
}
