package com.buylist.solomakha.buylistapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.buylist.solomakha.buylistapp.storage.db.RoomDatabaseImpl;
import com.buylist.solomakha.buylistapp.storage.db.model.Basket;

import java.util.List;

public class BasketLiveDataViewModel extends AndroidViewModel
{
    private RoomDatabaseImpl roomDatabaseImpl;

    public BasketLiveDataViewModel(Application application)
    {
        super(application);
        roomDatabaseImpl = RoomDatabaseImpl.getDatabase(application);
    }

    public List<Basket> getList()
    {
        return roomDatabaseImpl.basketDao().getAll();
    }
}
