package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.storage.db.model.Basket;
import com.buylist.solomakha.buylistapp.mvp.views.BasketView;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketPresenter
{
    void setView(BasketView view);

    void loadBasketList(boolean showProgress);

    void deleteBasket(Basket basket, boolean showProgress);

    void editBasket(Basket basket, boolean showProgress);

    void fillDbWithTestValues(boolean showProgress);

    void createBasket(String basketName, boolean showProgress);

    void openProductsByBasketId(long basketId);

    void openCreateBasketDialog();

    void openEditBasketDialog(Basket basket);
}
