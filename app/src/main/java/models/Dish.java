package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

/**
 * Created by bartoszlecki on 9/7/15.
 */
@Generated("org.jsonschema2pojo")
public class Dish implements Serializable, Orderable {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private double price;
    @Expose
    private String photo;
    @SerializedName("restaurant_id")
    @Expose
    private int restaurantId;

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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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

}
