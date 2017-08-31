package com.buylist.solomakha.buylistapp.storage.db;


import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.storage.Storage;
import com.buylist.solomakha.buylistapp.storage.db.model.Basket;
import com.buylist.solomakha.buylistapp.storage.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

import javax.inject.Inject;

public class Database implements Storage
{
    @Inject
    RoomDatabaseImpl database;

    public Database()
    {
        MainApp.getComponent().inject(this);
    }

    @Override
    public long createCategory(String name)
    {
        return database.categoryDao().insert(new Category(name));
    }

    @Override
    public List<Category> getCategories()
    {
        return database.categoryDao().getAll();
    }

    @Override
    public Category getCategoryById(long id)
    {
        return database.categoryDao().getById(id);
    }

    @Override
    public long createUnit(String name)
    {
        return database.unitDao().insert(new Unit(name));
    }

    @Override
    public List<Unit> getUnits()
    {
        return database.unitDao().getAll();
    }

    @Override
    public long createProduct(Product product)
    {
        return database.productDao().insert(product);
    }

    @Override
    public List<Product> getProducts()
    {
        return database.productDao().getAll();
    }

    @Override
    public long createBasket(String name)
    {
        return database.basketDao().insert(new Basket(name));
    }

    @Override
    public void deleteBasket(Basket basket)
    {
        database.basketDao().delete(basket);
    }

    @Override
    public List<Basket> getBaskets()
    {
        return database.basketDao().getAll();
    }

    @Override
    public List<ProductEmbedded> getProductsFromBasketId(long basketId)
    {
        return database.basketProductDao().getProductsByBasketId(basketId);
    }

    @Override
    public long assignProductToBasket(long productId, long basketId)
    {
        return database.basketProductDao().insert(new BasketProduct(productId, basketId));
    }

    @Override
    public long markProductAsBought(long basketId, long productId, boolean bought)
    {
        return 0;
    }
}