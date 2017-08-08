package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.ProductListModel;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;

import javax.inject.Inject;

/**
 * Created by asolomakha on 8/8/2017.
 */

public class ProductListPresenterImpl implements ProductListPresenter
{

    @Inject
    Storage storage;

    ProductListModel model;

    public ProductListPresenterImpl(ProductListModel model)
    {
        MainApp.getComponent().inject(this);
        this.model = model;
    }

    @Override
    public void loadProductsByBasketId(int basketId)
    {
        storage.getProductsFromBasket(basketId);
    }
}
