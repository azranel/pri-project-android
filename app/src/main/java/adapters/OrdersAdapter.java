package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.restauranto.restaurantoapp.R;

import org.w3c.dom.Text;

import java.util.List;

import models.Order;

/**
 * Created by bartoszlecki on 9/6/15.
 */
public class OrdersAdapter extends BaseAdapter {
    private final Context context;
    private List<Order> orders;

    public OrdersAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = (Order) getItem(position);
        View view = null;
        if(convertView == null)
            view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        else view = convertView;

        TextView dishesCount = (TextView) view.findViewById(R.id.OrderItem_dishesCount);
        TextView orderDate = (TextView) view.findViewById(R.id.OrderItem_orderDate);

        //TODO: Change from static to dynamic data injection
        dishesCount.setText("Dishes count: 4");
        orderDate.setText("27/07/2015 16:34");

        return view;
    }
}
