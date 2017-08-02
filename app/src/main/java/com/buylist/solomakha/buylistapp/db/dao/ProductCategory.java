package com.buylist.solomakha.buylistapp.db.dao;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;

import java.util.List;

/**
 * Created by asolomakha on 8/1/2017.
 */

public class ProductCategory
{
    @Embedded
    public Product product;

    @Relation(entity = Category.class, entityColumn = "id", parentColumn = "categoryId")
    public List<Category> category;
}
