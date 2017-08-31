package com.buylist.solomakha.buylistapp.mvp.models;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.buylist.solomakha.buylistapp.storage.db.RoomDatabaseImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class BasketModelTest
{

    @Inject
    BasketModel basketModel;

    @Before
    public void initDb()
    {
        RoomDatabaseImpl.setTestDatabase(InstrumentationRegistry.getContext());
    }

    @Test
    public void dbTest()
    {
    }

}
