package com.buylist.solomakha.buylistapp.bind;

import android.databinding.BaseObservable;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketPresenter;

/**
 * Created by asolomakha on 4/6/2017.
 */

public class BasketListViewModel extends BaseObservable
{
    private BasketPresenter mPresenter;

    public BasketListViewModel(BasketPresenter mPresenter)
    {
        this.mPresenter = mPresenter;
    }

    public void createBasket()
    {
        mPresenter.openCreateBasketDialog();
    }

    public void fillDataBase()
    {
        mPresenter.fillDbWithTestValues(true);
    }
}
