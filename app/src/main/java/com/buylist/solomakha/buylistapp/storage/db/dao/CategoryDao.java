package com.buylist.solomakha.buylistapp.storage.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.storage.db.model.Category;

import java.util.List;

@Dao
public interface CategoryDao
{
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE id == :categoryId")
    Category getById(long categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Category category);

    @Delete
    void delete(Category category);
}
