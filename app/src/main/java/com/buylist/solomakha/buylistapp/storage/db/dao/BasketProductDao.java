package com.buylist.solomakha.buylistapp.storage.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.storage.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.storage.db.model.embeded.ProductEmbedded;

import java.util.List;

@Dao
public interface BasketProductDao
{
    @Query("SELECT * FROM basketProduct")
    List<BasketProduct> getAll();

    @Query("SELECT * FROM basketProduct WHERE id == :basketProductId")
    BasketProduct getById(long basketProductId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(BasketProduct basketProduct);

    @Delete
    void delete(BasketProduct basket);

    //@Query("SELECT * FROM productEmbedded WHERE basketId == :basketId")
    @Query("SELECT * FROM basketProduct JOIN product ON basketProduct.productId == product.id WHERE basketProduct.basketId == :basketId")
    List<ProductEmbedded> getProductsByBasketId(long basketId);
}
