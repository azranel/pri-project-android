package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.restauranto.restaurantoapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Dish;
import utils.Utils;

/**
 * Created by bartoszlecki on 9/7/15.
 */
public class DishesAdapter extends BaseAdapter {
    private Context context;
    private List<Dish> dishes;

    public DishesAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dish = (Dish) getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dish_item, parent, false);
        } else view = convertView;

        ImageView photo = (ImageView) view.findViewById(R.id.DishItem_image);
        TextView name = (TextView) view.findViewById(R.id.DishItem_name);
        TextView price = (TextView) view.findViewById(R.id.DishItem_price);

        Picasso.with(context).load(Utils.HOST + dish.getPhoto()).into(photo);
        name.setText(dish.getName());
        price.setText(String.valueOf(dish.getPrice()));

        return view;
    }
}
