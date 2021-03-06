package com.buylist.solomakha.buylistapp.mvp.views;

import com.buylist.solomakha.buylistapp.storage.db.model.Basket;

import java.util.List;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketView
{
    void showBasketList(List<Basket> basketList);

    void showError(String errorMsg);

    void showMessage(String message);

    void showProductsByBasketId(long basketId);

    void showProgress(boolean show);

    void showCreateBasketDialog();

    void showEditBasketDialog(Basket basket);
}
