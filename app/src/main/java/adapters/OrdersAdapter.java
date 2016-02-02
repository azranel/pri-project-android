package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.restauranto.restaurantoapp.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
        Collections.sort(this.orders, new Comparator<Order>() {
            @Override
            public int compare(Order lhs, Order rhs) {
                return lhs.getCreatedAt().compareTo(rhs.getCreatedAt());
            }
        });
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

        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        dishesCount.setText("Ilość zamówień: " +
                String.valueOf(order.getDishIds().length + order.getSetsIds().length));
        orderDate.setText(dt.format(order.getCreatedAt()));

        return view;
    }

    public void removeOrderFromList(Order order) {
        orders.remove(order);
        notifyDataSetChanged();
    }
}
