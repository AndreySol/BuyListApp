package com.buylist.solomakha.buylistapp.mvp.presentors.impl;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.mvp.presentors.ProductPresenter;
import com.buylist.solomakha.buylistapp.mvp.views.ProductView;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by asolomakha on 8/8/2017.
 */

public class ProductPresenterImpl implements ProductPresenter
{
    @Inject
    ProductModel model;
    private ProductView view;

    public ProductPresenterImpl(ProductView view)
    {
        this.view = view;
        MainApp.getComponent().inject(this);

    }

    @Override
    public void loadProductsByBasketId(long basketId)
    {
        model.getProductsFromBasket(basketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Product>>()
                {
                    @Override
                    public void onSuccess(List<Product> value)
                    {
                        view.showProductsByBasketId(value);
                    }

                    @Override
                    public void onError(Throwable error)
                    {

                    }
                });
    }
}
