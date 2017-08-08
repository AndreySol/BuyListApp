package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.db.model.Product;

import java.util.List;

/**
 * Created by asolomakha on 8/8/2017.
 */

public interface ProductListPresenter
{
    void loadProductsByBasketId(int basketId);
}
