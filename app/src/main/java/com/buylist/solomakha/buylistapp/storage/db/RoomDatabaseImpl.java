package com.buylist.solomakha.buylistapp.storage.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.db.dao.BasketDao;
import com.buylist.solomakha.buylistapp.storage.db.dao.BasketProductDao;
import com.buylist.solomakha.buylistapp.storage.db.dao.CategoryDao;
import com.buylist.solomakha.buylistapp.storage.db.dao.ProductDao;
import com.buylist.solomakha.buylistapp.storage.db.dao.UnitDao;
import com.buylist.solomakha.buylistapp.storage.db.model.Basket;
import com.buylist.solomakha.buylistapp.storage.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;


@Database(exportSchema = false, entities = {Basket.class, BasketProduct.class, Category.class, Product.class, Unit.class}, version = 1)
public abstract class RoomDatabaseImpl extends RoomDatabase
{
    static RoomDatabaseImpl INSTANCE;

    public static RoomDatabaseImpl getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, RoomDatabaseImpl.class, "buyDatabase").build();
        }
        return INSTANCE;
    }

    public static RoomDatabaseImpl setTestDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.inMemoryDatabaseBuilder(context, RoomDatabaseImpl.class).build();
        }
        return INSTANCE;
    }

    public abstract BasketDao basketDao();

    public abstract CategoryDao categoryDao();

    public abstract ProductDao productDao();

    public abstract UnitDao unitDao();

    public abstract BasketProductDao basketProductDao();
}
