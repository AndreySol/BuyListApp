package com.buylist.solomakha.buylistapp.mvp.models.impl;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Single;

public class ProductModelImpl implements ProductModel
{
    @Inject
    Storage storage;

    public ProductModelImpl()
    {
        MainApp.getComponent().inject(this);
    }

    @Override
    public Single<List<ProductEmbedded>> getProductsFromBasket(final long basketId)
    {
        return Single.fromCallable(new Callable<List<ProductEmbedded>>()
        {
            @Override
            public List<ProductEmbedded> call() throws Exception
            {
                return storage.getProductsFromBasketId(basketId);
            }
        });
    }
}
