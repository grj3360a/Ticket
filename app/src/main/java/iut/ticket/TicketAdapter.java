package iut.ticket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.TicketDao;
import iut.ticket.dao.TicketWithProducts;

public class TicketAdapter extends ArrayAdapter<TicketWithProducts> implements View.OnClickListener {

    private final List<TicketWithProducts> dataSet;
    private final Context context;
    private final TicketDao ticketDao;

    public TicketAdapter(List<TicketWithProducts> data, Context context) {
        super(context, R.layout.history_list_view, data);
        this.dataSet = data;
        this.context = context;
        this.ticketDao = AppDB.getDB(context).ticketDao();;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click !", Toast.LENGTH_SHORT).show();
        int idTicket = Integer.parseInt(((TextView) v.findViewById(R.id.idTicket)).getText().toString());
        TicketWithProducts ticketWithProducts = ticketDao.getTicketWithProductsFromID(idTicket);
        ticketDao.delete(ticketWithProducts);
        this.remove(ticketWithProducts);
    }

    @Override
    public int getCount(){
        return this.dataSet.size();
    }

    @Override
    public TicketWithProducts getItem(int i){
        return this.dataSet.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public void add(TicketWithProducts ticketWithProducts){
        this.dataSet.add(ticketWithProducts);
        super.add(ticketWithProducts);
        this.notifyDataSetChanged();
    }

    @Override
    public void remove(TicketWithProducts ticketWithProducts){
        this.dataSet.remove(ticketWithProducts);
        super.remove(ticketWithProducts);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TicketWithProducts dataModel = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_list_view, parent, false);
            convertView.setOnClickListener(this);
        }

        ImageView imageView = convertView.findViewById(R.id.ticketPhoto);
        TextView idView = convertView.findViewById(R.id.idTicket);
        TextView productsView = convertView.findViewById(R.id.products);
        TextView totalView = convertView.findViewById(R.id.totalTicket);

        imageView.setImageBitmap(dataModel.ticket.getImage());
        idView.setText(dataModel.ticket.ticket_id + "");
        productsView.setText(context.getResources().getQuantityString(R.plurals.products, dataModel.products.size(), dataModel.products.size()));
        totalView.setText(context.getString(R.string.total, dataModel.total()));

        return convertView;
    }
}
