package com.buylist.solomakha.buylistapp.mvp.models.impl;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.model.Basket;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.mvp.models.BasketModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Single;

/**
 * Created by asolomakha on 3/22/2017.
 */

public class BasketModelImpl implements BasketModel
{
    @Inject
    Storage storage;

    public BasketModelImpl()
    {
        MainApp.getComponent().inject(this);
    }

    @Override
    public Single<List<Basket>> getBasketList()
    {
        return Single.fromCallable(new Callable<List<Basket>>()
        {
            @Override
            public List<Basket> call() throws Exception
            {
                return storage.getBaskets();
            }
        });
    }

    @Override
    public Single<Integer> deleteBasket(final Basket basket)
    {
        return Single.fromCallable(new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                storage.deleteBasket(basket);
                return 0;
            }
        });
    }

    @Override
    public Single<Integer> editBasket(final Basket basket)
    {
        return Single.fromCallable(new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                //return storage.editBasket(basket);
                return 0;
            }
        });
    }

    @Override
    public Single<Boolean> createTestValues()
    {
        return Single.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call()
            {
                storage.createCategory("Others");
                storage.createCategory("Dairy");
                storage.createCategory("Meat");
                long categoryFruitsId = storage.createCategory("Fruit");

                long categoryAppliancesId = storage.createCategory("Appliances");

                long unitKgId = storage.createUnit("kg");
                storage.createUnit("L.");
                long unitPcsId = storage.createUnit("pcs");

                long listPostProductsId = storage.createBasket("PostProducts");
                long listPostClothesId = storage.createBasket("PostClothes");
                long listPostAppliancesId = storage.createBasket("PostAppliances");

                //ПРОДУКТ:
                //продукты  привязка к UNIT and CATEGORY
                //Apple - не внесен  в список
                Product product = new Product();
                product.setName("Apple");
                product.setCategoryId(categoryFruitsId);
                product.setPriority(false);
                product.setQuantity(2);
                product.setUnitId(unitKgId);
                long productId = storage.createProduct(product);
                storage.assignProductToBasket(listPostProductsId, productId);//связка  со списком

                // Kettle - не внесен  в список
                Product product1 = new Product();
                product1.setName("Kettle");
                product1.setCategoryId(categoryAppliancesId);
                product1.setPriority(false);
                product1.setQuantity(1);
                product1.setUnitId(unitPcsId);
                long productId1 = storage.createProduct(product1);
                storage.assignProductToBasket(listPostProductsId, productId1);//связка  со списком
                storage.assignProductToBasket(listPostProductsId, productId1);//связка  со списком

                // Banana -  внесен  в список
                Product product2 = new Product();
                product2.setName("Banana");
                product2.setCategoryId(categoryFruitsId);
                product2.setPriority(false);
                product2.setQuantity(1.5f);
                product2.setUnitId(unitKgId);
                long productId2 = storage.createProduct(product2);
                storage.assignProductToBasket(listPostProductsId, productId2);//связка  со списком

                // Orange -  внесен  в список
                Product product3 = new Product();
                product3.setName("Orange");
                product3.setCategoryId(categoryFruitsId);
                product3.setPriority(false);
                product3.setQuantity(2.5f);
                product3.setUnitId(unitKgId);
                long productId3 = storage.createProduct(product3);
                storage.assignProductToBasket(listPostProductsId, productId3);//связка  со списком
                return true;
            }
        });
    }

    @Override
    public Single<Long> createBasket(final String basketName)
    {
        return Single.fromCallable(new Callable<Long>()
        {
            @Override
            public Long call() throws Exception
            {
                return storage.createBasket(basketName);
            }
        });
    }
}
