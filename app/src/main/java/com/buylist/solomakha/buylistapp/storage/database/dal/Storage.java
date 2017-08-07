package com.buylist.solomakha.buylistapp.storage.database.dal;


import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.db.model.Unit;

import java.util.List;

/**
 * Created by asolomakha on 1/4/2016.
 */
public interface Storage
{
    long createCategory(String categoryName);
    List<Category> getCategories();
    Category getCategoryById(long id);

    long createUnit(String unit);
    List<Unit> getUnits();

    long createProduct(Product product);
    List<Product> getProducts();

    long createBasket(String basketName);
    void deleteBasket(Basket basket);
    List<Basket> getBaskets();

    List<Product> getProductsFromBasket(long basketId);

    long assignProductToBasket(long basketId, long productId);

    long markProductAsBought(long basketId, long productId, boolean bought);
}
