package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by bartoszlecki on 9/5/15.
 */
@Generated("org.jsonschema2pojo")
public class Restaurant {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String logo;
    @SerializedName("dishes_url")
    @Expose
    private String dishesUrl;
    @SerializedName("sets_url")
    @Expose
    private String setsUrl;

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
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return
     * The dishesUrl
     */
    public String getDishesUrl() {
        return dishesUrl;
    }

    /**
     *
     * @param dishesUrl
     * The dishes_url
     */
    public void setDishesUrl(String dishesUrl) {
        this.dishesUrl = dishesUrl;
    }

    /**
     *
     * @return
     * The setsUrl
     */
    public String getSetsUrl() {
        return setsUrl;
    }

    /**
     *
     * @param setsUrl
     * The sets_url
     */
    public void setSetsUrl(String setsUrl) {
        this.setsUrl = setsUrl;
    }

}
