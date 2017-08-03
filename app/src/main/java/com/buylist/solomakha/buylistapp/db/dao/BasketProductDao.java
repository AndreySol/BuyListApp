package com.buylist.solomakha.buylistapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.db.model.BasketProduct;

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

}
