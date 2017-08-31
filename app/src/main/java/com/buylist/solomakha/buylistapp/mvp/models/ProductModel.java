package com.buylist.solomakha.buylistapp.mvp.models;

import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

import rx.Single;

/**
 * Created by asolomakha on 8/8/2017.
 */

public interface ProductModel
{
    Single<List<ProductEmbedded>> getProductsFromBasket(long basketId);

    Single<List<Category>> getCategories();

    Single<List<Unit>> getUnits();

    Single<Long> createProduct(Product product);

    Single<Long> assignProductToBasket(long productId, long basketId);
}
