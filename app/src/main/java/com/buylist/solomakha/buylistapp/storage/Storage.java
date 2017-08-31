package com.buylist.solomakha.buylistapp.storage;


import com.buylist.solomakha.buylistapp.storage.db.model.Basket;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

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

    List<ProductEmbedded> getProductsFromBasketId(long basketId);

    long assignProductToBasket(long productId, long basketId);

    long markProductAsBought(long basketId, long productId, boolean bought);
}