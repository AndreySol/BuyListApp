package com.buylist.solomakha.buylistapp.mvp.views;

import com.buylist.solomakha.buylistapp.db.model.Product;

import java.util.List;

/**
 * Created by asolomakha on 8/9/2017.
 */

public interface ProductView
{
    void showProductsByBasketId(List<Product> productList);
}
