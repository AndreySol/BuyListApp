package com.buylist.solomakha.buylistapp.mvp.models.impl;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Single;
import rx.functions.Func0;

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

    @Override
    public Single<List<Category>> getCategories()
    {
        return Single.fromCallable(new Func0<List<Category>>()
        {
            @Override
            public List<Category> call()
            {
                return storage.getCategories();
            }
        }) ;
    }

    @Override
    public Single<List<Unit>> getUnits()
    {
        return Single.fromCallable(new Func0<List<Unit>>()
        {
            @Override
            public List<Unit> call()
            {
                return storage.getUnits();
            }
        }) ;
    }

    @Override
    public Single<Long> createProduct(final Product product)
    {
        return Single.fromCallable(new Func0<Long>()
        {
            @Override
            public Long call()
            {
                return storage.createProduct(product);
            }
        });
    }

    @Override
    public Single<Long> assignProductToBasket(final long productId, final long basketId)
    {
        return Single.fromCallable(new Func0<Long>()
        {
            @Override
            public Long call()
            {
                return storage.assignProductToBasket(productId, basketId);
            }
        });
    }
}
