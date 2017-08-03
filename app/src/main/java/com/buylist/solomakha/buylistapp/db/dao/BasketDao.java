package com.buylist.solomakha.buylistapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.db.dao.embeded.BasketProducts;
import com.buylist.solomakha.buylistapp.db.model.Basket;

import java.util.List;

@Dao
public interface BasketDao
{
    @Query("SELECT * FROM basket")
    List<Basket> getAll();

    @Query("SELECT * FROM basket WHERE id == :basketId")
    Basket getById(long basketId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Basket basket);

    @Delete
    void delete(Basket basket);

    @Query("SELECT * FROM basketProduct WHERE basketId == :basketId")
    BasketProducts getBasketProducts(long basketId);
}
