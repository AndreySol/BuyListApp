package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.storage.db.model.Product;

/**
 * Created by asolomakha on 8/8/2017.
 */

public interface ProductPresenter
{
    void loadProductsByBasketId(long basketId);

    void openAddProductDialog();

    void createProductAndAssignToBasket(Product product, long l);
}
