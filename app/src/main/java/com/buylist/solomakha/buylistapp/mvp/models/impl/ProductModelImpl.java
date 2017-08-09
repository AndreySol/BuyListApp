package com.buylist.solomakha.buylistapp.mvp.models.impl;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Single;

/**
 * Created by asolomakha on 8/8/2017.
 */

public class ProductModelImpl implements ProductModel
{
    @Inject
    Storage storage;

    public ProductModelImpl()
    {
        MainApp.getComponent().inject(this);
    }

    @Override
    public Single<List<Product>> getProductsFromBasket(final long basketId)
    {
        return Single.fromCallable(new Callable<List<Product>>()
        {
            @Override
            public List<Product> call() throws Exception
            {
                return storage.getProductsFromBasket(basketId);
            }
        });
    }
}
