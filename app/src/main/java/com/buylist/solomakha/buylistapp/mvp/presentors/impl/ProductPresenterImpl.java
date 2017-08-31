package com.buylist.solomakha.buylistapp.mvp.presentors.impl;

import android.util.Pair;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.ProductModel;
import com.buylist.solomakha.buylistapp.mvp.presentors.ProductPresenter;
import com.buylist.solomakha.buylistapp.mvp.views.ProductView;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by asolomakha on 8/8/2017.
 */

public class ProductPresenterImpl implements ProductPresenter
{
    @Inject
    ProductModel model;
    private ProductView view;

    public ProductPresenterImpl(ProductView view)
    {
        this.view = view;
        MainApp.getComponent().inject(this);

    }

    @Override
    public void loadProductsByBasketId(long basketId)
    {
        model.getProductsFromBasket(basketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<ProductEmbedded>>()
                {
                    @Override
                    public void onSuccess(List<ProductEmbedded> value)
                    {
                        view.showProductsByBasketId(value);
                    }

                    @Override
                    public void onError(Throwable error)
                    {

                    }
                });
    }

    @Override
    public void openAddProductDialog()
    {
        Single.zip(
                model.getUnits().subscribeOn(Schedulers.io()),
                model.getCategories().subscribeOn(Schedulers.io()),
                new Func2<List<Unit>, List<Category>, Pair<List<Unit>, List<Category>>>()
                {
                    @Override
                    public Pair<List<Unit>, List<Category>> call(List<Unit> units, List<Category> categories)
                    {
                        return new Pair<>(units, categories);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Pair<List<Unit>, List<Category>>>()
                {
                    @Override
                    public void call(Pair<List<Unit>, List<Category>> pair)
                    {
                        view.showAddProductDialog(pair.first, pair.second);
                    }
                });
    }

    @Override
    public void createProductAndAssignToBasket(final Product product, final long basketId)
    {
        model.createProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {
                    @Override
                    public void call(Long productId)
                    {
                        if (productId > -1)
                        {
                            model.assignProductToBasket(productId, basketId)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<Long>()
                                    {
                                        @Override
                                        public void call(Long aLong)
                                        {
                                            loadProductsByBasketId(basketId);
                                        }
                                    });
                        }
                    }
                });
    }
}
