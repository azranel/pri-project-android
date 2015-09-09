package services;

import android.content.Context;
import android.widget.ListView;

import adapters.DishesAdapter;
import adapters.RestaurantSetsAdapter;
import models.Order;

/**
 * Created by bartoszlecki on 9/9/15.
 */
public class FetchFoodForOrderService {
    private Context context;
    private RestaurantSetsAdapter setsAdapter;
    private DishesAdapter dishesAdapter;
    private ListView setsListView;
    private ListView dishesListView;
    private Order order;

    public FetchFoodForOrderService(Context context,
                                    RestaurantSetsAdapter setsAdapter,
                                    DishesAdapter dishesAdapter,
                                    ListView setsListView,
                                    ListView dishesListView,
                                    Order order) {
        this.context = context;
        this.setsAdapter = setsAdapter;
        this.dishesAdapter = dishesAdapter;
        this.setsListView = setsListView;
        this.dishesListView = dishesListView;
        this.order = order;
    }

    public void call() {
        int[] dishIds = order.getDishIds();
    }
}
