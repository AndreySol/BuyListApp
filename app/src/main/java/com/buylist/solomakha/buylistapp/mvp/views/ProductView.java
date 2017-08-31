package com.buylist.solomakha.buylistapp.mvp.views;

import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

/**
 * Created by asolomakha on 8/9/2017.
 */

public interface ProductView
{
    void showProductsByBasketId(List<ProductEmbedded> productList);

    void showAddProductDialog(List<Unit> units, List<Category> categories);
}
