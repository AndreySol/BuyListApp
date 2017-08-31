package com.buylist.solomakha.buylistapp.storage.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by asolomakha on 8/28/2017.
 */

public abstract class TestRoomDatabase extends RoomDatabaseImpl
{
    public static RoomDatabaseImpl getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.inMemoryDatabaseBuilder(context, RoomDatabaseImpl.class).build();
        }
        return INSTANCE;
    }
}
