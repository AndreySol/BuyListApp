package com.buylist.solomakha.buylistapp.storage.database.dal;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.db.AppDatabase;
import com.buylist.solomakha.buylistapp.db.dao.embeded.BasketProducts;
import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.db.model.Unit;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * Created by asolomakha on 8/7/2017.
 */

public class RoomDatabase implements Storage
{
    @Inject
    AppDatabase database;

    public RoomDatabase()
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
        Logger.getLogger("TestLogger").log(Level.INFO, "getBaskets");
        return database.basketDao().getAll();
    }

    @Override
    public List<Product> getProductsFromBasket(long id)
    {
        BasketProducts basketProducts = database.basketDao().getBasketProducts(id);
        return basketProducts.products;
    }

    @Override
    public long assignProductToBasket(long basketId, long productId)
    {
        return database.basketProductDao().insert(new BasketProduct(basketId, productId));
    }

    @Override
    public long markProductAsBought(long basketId, long productId, boolean bought)
    {
        return 0;
    }
}
