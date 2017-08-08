package com.buylist.solomakha.buylistapp.mvp.presentors;

import android.util.Log;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;

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

public class BasketListPresenterImpl implements BasketListPresenter
{
    @Inject
    BasketListModel basketListModel;

    private BasketListView basketListView;

    public BasketListPresenterImpl()
    {
        MainApp.getComponent().inject(this);
    }

    public void setView(BasketListView view)
    {
        basketListView = view;
    }

    @Override
    public void loadBasketList(final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketListView.showProgress(true);
            Logger.getLogger("TestOrCh").log(Level.INFO, "showProgress: " + basketListView);
        }

        basketListModel.getBasketList()
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
                            basketListView.showBasketList(value);
                            if (showProgress)
                            {
                                basketListView.showProgress(false);
                                Logger.getLogger("TestOrCh").log(Level.INFO, "closeProgress: " + basketListView);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable error)
                    {
                        if (hasBasketView())
                        {
                            basketListView.showError(error.getMessage());
                            if (showProgress)
                            {
                                basketListView.showProgress(false);
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
            basketListView.showProgress(true);
        }
        Single.concat(basketListModel.deleteBasket(basket), basketListModel.getBasketList())
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
                            basketListView.showBasketList((List<Basket>) o);
                            if (showProgress)
                            {
                                basketListView.showProgress(false);
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
            basketListView.showProgress(true);
        }

        Single.concat(basketListModel.editBasket(basket), basketListModel.getBasketList())
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
                            basketListView.showError("Cannot edit basket: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Object o)
                    {
                        if (hasBasketView())
                        {
                            if (showProgress)
                            {
                                basketListView.showProgress(false);
                            }
                            basketListView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void fillDbWithTestValues(final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketListView.showProgress(true);
        }
        Single.concat(basketListModel.createTestValues(), basketListModel.getBasketList())
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
                                basketListView.showProgress(false);
                            }
                            basketListView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void createBasket(String basketName, final boolean showProgress)
    {
        if (showProgress && hasBasketView())
        {
            basketListView.showProgress(true);
        }
        Single.concat(basketListModel.createBasket(basketName), basketListModel.getBasketList())
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
                                basketListView.showProgress(false);
                            }
                            basketListView.showBasketList((List<Basket>) o);
                        }
                    }
                });
    }

    @Override
    public void openProductsByBasketId(long basketId)
    {
        if (hasBasketView())
        {
            basketListView.showProductsByBasket(basketId);
        }
    }

    @Override
    public void openCreateBasketDialog()
    {
        if (hasBasketView())
        {
            basketListView.showCreateBasketDialog();
        }
    }

    @Override
    public void openEditBasketDialog(Basket basket)
    {
        if (hasBasketView())
        {
            basketListView.showEditBasketDialog(basket);
        }
    }

    private boolean hasBasketView()
    {
        return basketListView != null;
    }
}
