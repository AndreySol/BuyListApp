package com.buylist.solomakha.buylistapp.mvp.models;


import com.buylist.solomakha.buylistapp.db.model.Basket;

import java.util.List;

import rx.Single;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketListModel
{
    Single<List<Basket>> getBasketList();

    Single<Integer> deleteBasket(Basket basket);

    Single<Integer> editBasket(Basket basket);

    Single<Boolean> createTestValues();

    Single<Long> createBasket(String basketName);
}
