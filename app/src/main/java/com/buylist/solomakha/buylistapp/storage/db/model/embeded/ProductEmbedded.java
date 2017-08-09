package com.buylist.solomakha.buylistapp.storage.db.model.embeded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.buylist.solomakha.buylistapp.storage.db.model.Category;
import com.buylist.solomakha.buylistapp.storage.db.model.Product;
import com.buylist.solomakha.buylistapp.storage.db.model.Unit;

import java.util.List;

public class ProductEmbedded
{
    @Embedded
    public Product product;

    @Relation(entity = Category.class, entityColumn = "id", parentColumn = "categoryId")
    public List<Category> categories;

    @Relation(entity = Unit.class, entityColumn = "id", parentColumn = "unitId")
    public List<Unit> units;
}
