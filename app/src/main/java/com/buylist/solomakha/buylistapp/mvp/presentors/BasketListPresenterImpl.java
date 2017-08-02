package com.buylist.solomakha.buylistapp.mvp.presentors;

import android.util.Log;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.AppDatabase;
import com.buylist.solomakha.buylistapp.db.dao.ProductCategory;
import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.db.model.Unit;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BasketListPresenterImpl implements BasketListPresenter
{
    @Inject
    BasketListModel basketListModel;

    @Inject
    AppDatabase database;

    private WeakReference<BasketListView> basketListView;

    public BasketListPresenterImpl()
    {
        MainApp.getComponent().inject(this);
    }

    public void setView(BasketListView view)
    {
        basketListView = new WeakReference<>(view);
    }

    @Override
    public void loadBasketList(final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
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
                        basketListView.get().showBasketList(value);
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                            Logger.getLogger("TestOrCh").log(Level.INFO, "closeProgress: " + basketListView);
                        }
                    }

                    @Override
                    public void onError(Throwable error)
                    {
                        basketListView.get().showError(error.getMessage());
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                    }
                });
    }

    @Override
    public void deleteBasket(long id, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
        }
        Single.concat(basketListModel.deleteBasket(id), basketListModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        basketListView.get().showBasketList((List<Basket>)o);
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                    }
                });
    }

    @Override
    public void editBasket(Basket basket, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
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
                        basketListView.get().showError("Cannot edit basket: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o)
                    {
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                        basketListView.get().showBasketList((List<Basket>)o);
                    }
                });
    }

    @Override
    public void fillDbWithTestValues(final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
        }
        /*Single.concat(basketListModel.createTestValues(), basketListModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                        basketListView.get().showBasketList((List<Basket>)o);
                    }
                });*/


        Single.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call()
            {
                //database.query("DELETE FROM buyDatabase", null);

                database.basketDao().insert(new com.buylist.solomakha.buylistapp.db.model.Basket("TestBasket1"));
                long testBasket2Id = database.basketDao().insert(new com.buylist.solomakha.buylistapp.db.model.Basket("TestBasket2"));
                database.basketDao().insert(new com.buylist.solomakha.buylistapp.db.model.Basket("TestBasket3"));

                database.categoryDao().insert(new Category("TestCategory1"));
                long testCategory2Id = database.categoryDao().insert(new Category("TestCategory2"));
                database.categoryDao().insert(new Category("TestCategory3"));

                database.unitDao().insert(new Unit("TestUnit1"));
                long testUnit2Id = database.unitDao().insert(new Unit("TestUnit2"));


                Product product = new Product();
                product.setName("TestProduct1");
                product.setBought(false);
                product.setPriority(false);
                product.setQuantity(1);
                product.setCategoryId(testCategory2Id);
                product.setUnitId(testUnit2Id);

                long testProduct1Id = database.productDao().insert(product);

                Product product1 = database.productDao().getById(testProduct1Id);

                ProductCategory productCategory = database.productDao().getProductCategory(testProduct1Id);

                return true;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>()
                {
                    @Override
                    public void call(Boolean aBoolean)
                    {

                    }
                });

    }

    @Override
    public void createBasket(String basketName, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
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
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                        basketListView.get().showBasketList((List<Basket>)o);
                    }
                });
    }

    @Override
    public void openProductsInBasket(long basketId)
    {
        basketListView.get().showProductsByBasket(basketId);
    }

    @Override
    public void openCreateBasketDialog()
    {
        basketListView.get().showCreateBasketDialog();
    }

    @Override
    public void openEditBasketDialog(Basket basket)
    {
        basketListView.get().showEditBasketDialog(basket);
    }
}
