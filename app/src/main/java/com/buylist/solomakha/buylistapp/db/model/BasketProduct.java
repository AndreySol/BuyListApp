package com.buylist.solomakha.buylistapp.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BasketProduct
{
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ForeignKey(entity = Basket.class, childColumns = "basketId", parentColumns = "id")
    private long basketId;
    @ForeignKey(entity = Product.class, childColumns = "productId", parentColumns = "id")
    private long productId;
    private boolean bought;

    public BasketProduct(Long basketId, Long productId)
    {
        this.basketId = basketId;
        this.productId = productId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getBasketId()
    {
        return basketId;
    }

    public void setBasketId(long basketId)
    {
        this.basketId = basketId;
    }

    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
    }

    public boolean isBought()
    {
        return bought;
    }

    public void setBought(boolean bought)
    {
        this.bought = bought;
    }
}
