package com.buylist.solomakha.buylistapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.buylist.solomakha.buylistapp.storage.db.AppDatabase;
import com.buylist.solomakha.buylistapp.storage.db.model.Basket;

import java.util.List;

public class BasketLiveDataViewModel extends AndroidViewModel
{
    private AppDatabase appDatabase;

    public BasketLiveDataViewModel(Application application)
    {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
    }

    public List<Basket> getList()
    {
        return appDatabase.basketDao().getAll();
    }
}
