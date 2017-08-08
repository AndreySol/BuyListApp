package com.buylist.solomakha.buylistapp.mvp.models;

import com.buylist.solomakha.buylistapp.db.model.Product;

import java.util.List;

/**
 * Created by asolomakha on 8/8/2017.
 */

public interface ProductListModel
{
    void showProductList(List<Product> productList);
}
