package com.buylist.solomakha.buylistapp.db.dao.embeded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.buylist.solomakha.buylistapp.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.db.model.Product;

import java.util.List;

public class BasketProducts
{
    @Embedded
    public BasketProduct basketProduct;

    @Relation(parentColumn = "productId", entity = Product.class, entityColumn = "id")
    public List<Product> products;
}
