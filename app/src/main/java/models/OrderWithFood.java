package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by bartoszlecki on 9/9/15.
 */
@Generated("org.jsonschema2pojo")
public class OrderWithFood {

    @Expose
    private int id;
    @SerializedName("restaurant_id")
    @Expose
    private int restaurantId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Expose
    private List<Dish> dishes = new ArrayList<Dish>();
    @SerializedName("restaurant_sets")
    @Expose
    private List<RestaurantSet> restaurantSets = new ArrayList<RestaurantSet>();

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The restaurantId
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     *
     * @param restaurantId
     * The restaurant_id
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     *
     * @param dishes
     * The dishes
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     *
     * @return
     * The restaurantSets
     */
    public List<RestaurantSet> getRestaurantSets() {
        return restaurantSets;
    }

    /**
     *
     * @param restaurantSets
     * The restaurant_sets
     */
    public void setRestaurantSets(List<RestaurantSet> restaurantSets) {
        this.restaurantSets = restaurantSets;
    }

}
