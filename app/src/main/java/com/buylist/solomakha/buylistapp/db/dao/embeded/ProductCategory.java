package com.buylist.solomakha.buylistapp.db.dao.embeded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.db.model.Unit;

import java.util.List;

import okhttp3.internal.Util;

public class ProductCategory
{
    @Embedded
    public Product product;

    @Relation(entity = Category.class, entityColumn = "id", parentColumn = "categoryId")
    public List<Category> categories;

    @Relation(entity = Unit.class, entityColumn = "id", parentColumn = "unitId")
    public List<Unit> units;
}
