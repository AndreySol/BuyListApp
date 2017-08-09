package com.buylist.solomakha.buylistapp.dagger.module;

import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.mvp.models.impl.ProductModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by asolomakha on 8/9/2017.
 */

@Module
@Singleton
public class ProductModule
{
    @Provides
    @Singleton
    ProductModel provideProductListModel()
    {
        return new ProductModelImpl();
    }
}
