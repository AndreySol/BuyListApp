package com.buylist.solomakha.buylistapp.mvp.models;

import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

import rx.Single;

/**
 * Created by asolomakha on 8/8/2017.
 */

public interface ProductModel
{
    Single<List<ProductEmbedded>> getProductsFromBasket(long basketId);
}
