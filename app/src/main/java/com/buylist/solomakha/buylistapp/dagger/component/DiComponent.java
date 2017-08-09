package com.buylist.solomakha.buylistapp.dagger.component;

import com.buylist.solomakha.buylistapp.dagger.module.BasketModule;
import com.buylist.solomakha.buylistapp.dagger.module.DbModule;
import com.buylist.solomakha.buylistapp.dagger.module.ProductModule;
import com.buylist.solomakha.buylistapp.mvp.models.impl.BasketModelImpl;
import com.buylist.solomakha.buylistapp.mvp.models.impl.ProductModelImpl;
import com.buylist.solomakha.buylistapp.mvp.presentors.impl.BasketPresenterImpl;
import com.buylist.solomakha.buylistapp.mvp.presentors.impl.ProductPresenterImpl;
import com.buylist.solomakha.buylistapp.storage.db.RoomDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BasketModule.class, ProductModule.class, DbModule.class})
public interface DiComponent
{
    void inject(BasketModelImpl comp);
    void inject(BasketPresenterImpl comp);

    void inject(ProductModelImpl comp);
    void inject(ProductPresenterImpl comp);

    void inject(RoomDatabase comp);
}
