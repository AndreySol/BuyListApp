package com.buylist.solomakha.buylistapp.storage.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.storage.db.model.Product;

import java.util.List;

@Dao
public interface ProductDao
{
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE id == :productId")
    Product getById(long productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Product product);

    @Delete
    void delete(Product product);
}
