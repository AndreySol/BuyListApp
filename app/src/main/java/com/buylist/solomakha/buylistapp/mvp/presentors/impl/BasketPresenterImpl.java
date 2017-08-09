package com.buylist.solomakha.buylistapp.mvp.presentors.impl;

import android.util.Log;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.mvp.models.BasketModel;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketPresenter;
import com.buylist.solomakha.buylistapp.mvp.views.BasketView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BasketPresenterImpl implements BasketPresenter
{
    @Inject
    BasketModel basketModel;

    private BasketView basketView;

    public BasketPresenterImpl()
    {
        MainApp.getComponent().inject(this);
    }

    public void setView(BasketView view)
    {
        basketView = view;
    }

    @Override
    public void loadBasketList(final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketView.showProgress(true);
            Logger.getLogger("TestOrCh").log(Level.INFO, "showProgress: " + basketView);
        }

        basketModel.getBasketList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Basket>>()
                {
                    @Override
                    public void onSuccess(List<Basket> value)
                    {
                        Log.i("TestRx", "subscribe Thread: " + Thread.currentThread().getId());
                        if (hasBasketView())
                        {
                            basketView.showBasketList(value);
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                                Logger.getLogger("TestOrCh").log(Level.INFO, "closeProgress: " + basketView);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable error)
                    {
                        if (hasBasketView())
                        {
                            basketView.showError(error.getMessage());
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                            }
                        }
                    }
                });
    }

    @Override
    public void deleteBasket(Basket basket, final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketView.showProgress(true);
        }
        Single.concat(basketModel.deleteBasket(basket), basketModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (hasBasketView())
                        {
                            basketView.showBasketList((List<Basket>) o);
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                            }
                        }
                    }
                });
    }

    @Override
    public void editBasket(Basket basket, final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketView.showProgress(true);
        }

        Single.concat(basketModel.editBasket(basket), basketModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Subscriber<Object>()
                {
                    @Override
                    public void onCompleted()
                    {
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        if (hasBasketView())
                        {
                            basketView.showError("Cannot edit basket: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Object o)
                    {
                        if (hasBasketView())
                        {
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                            }
                            basketView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void fillDbWithTestValues(final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketView.showProgress(true);
        }
        Single.concat(basketModel.createTestValues(), basketModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (hasBasketView())
                        {
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                            }
                            basketView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void createBasket(String basketName, final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketView.showProgress(true);
        }
        Single.concat(basketModel.createBasket(basketName), basketModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (hasBasketView())
                        {
                            if (showProgress)
                            {
                                basketView.showProgress(false);
                            }
                            basketView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void openProductsByBasketId(long basketId)
    {
        if (hasBasketView())
        {
            basketView.showProductsByBasket(basketId);
        }
    }

    @Override
    public void openCreateBasketDialog()
    {
        if (hasBasketView())
        {
            basketView.showCreateBasketDialog();
        }
    }

    @Override
    public void openEditBasketDialog(Basket basket)
    {
        if (hasBasketView())
        {
            basketView.showEditBasketDialog(basket);
        }
    }

    private boolean hasBasketView()
    {
        return basketView != null;
    }
}
