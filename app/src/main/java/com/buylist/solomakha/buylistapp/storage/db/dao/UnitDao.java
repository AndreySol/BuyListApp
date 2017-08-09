package com.buylist.solomakha.buylistapp.storage.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.storage.db.model.Unit;

import java.util.List;

@Dao
public interface UnitDao
{
    @Query("SELECT * FROM unit")
    List<Unit> getAll();

    @Query("SELECT * FROM unit WHERE id == :unitId")
    Unit getById(long unitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Unit unit);

    @Delete
    void delete(Unit unit);
}
