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

import models.RestaurantSet;
import utils.Utils;

/**
 * Created by bartoszlecki on 9/8/15.
 */
public class RestaurantSetsAdapter extends BaseAdapter {
    private Context context;
    private List<RestaurantSet> restaurantSets;

    public RestaurantSetsAdapter(Context context, List<RestaurantSet> restaurantSets) {
        this.context = context;
        this.restaurantSets = restaurantSets;
    }

    @Override
    public int getCount() {
        return restaurantSets.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantSets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantSet restaurantSet = (RestaurantSet) getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dish_item, parent, false);
        } else view = convertView;

        ImageView photo = (ImageView) view.findViewById(R.id.DishItem_image);
        TextView name = (TextView) view.findViewById(R.id.DishItem_name);
        TextView price = (TextView) view.findViewById(R.id.DishItem_price);

        Log.v("RESTAURANTO", Utils.HOST + restaurantSet.getPhoto());
        Picasso.with(context).load(Utils.HOST + restaurantSet.getPhoto()).into(photo);
        name.setText(restaurantSet.getName());
        price.setText(String.valueOf(restaurantSet.getPrice()));

        return view;
    }
}
