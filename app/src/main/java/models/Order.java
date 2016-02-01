package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bartoszlecki on 9/6/15.
 */
public class Order {
    @Expose
    private int id;
    @SerializedName("restaurant_id")
    @Expose
    private int restaurantId;
    @SerializedName("restaurant_set_ids")
    @Expose
    private int[] setsIds;
    @SerializedName("dish_ids")
    @Expose
    private int[] dishIds;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;


    public Order(int restaurantId, int[] dishIds, int[] setsIds) {
        this.restaurantId = restaurantId;
        this.dishIds = dishIds;
        this.setsIds = setsIds;
    }

    public int[] getSetsIds() {
        return setsIds;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setSetsIds(int[] setsIds) {
        this.setsIds = setsIds;
    }

    public int[] getDishIds() {
        return dishIds;
    }

    public void setDishIds(int[] dishIds) {
        this.dishIds = dishIds;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
