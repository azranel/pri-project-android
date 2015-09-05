package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restauranto.restaurantoapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Restaurant;
import utils.Utils;

/**
 * Created by bartoszlecki on 9/5/15.
 */
public class RestaurantAdapter extends BaseAdapter {

    private final Context context;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurant restaurant = (Restaurant) getItem(position);
        View view = null;
        if(convertView == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        else view = convertView;
        ImageView image = (ImageView) view.findViewById(R.id.Restaurant_item_image);
        TextView name = (TextView) view.findViewById(R.id.Restaurant_item_name);
        Log.v("RESTAURANTO", Utils.HOST + restaurant.getLogo());
        Picasso.with(context).load(Utils.HOST + restaurant.getLogo()).into(image);
        name.setText(restaurant.getName());
        return view;
    }
}
