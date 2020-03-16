package iut.ticket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    private final Context context;

    public TicketAdapter(Context context) {
        super(context, R.layout.history_list_view, AppDB.getTicketDao().getTicketWithProducts());
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        int idTicket = Integer.parseInt(((TextView) v.findViewById(R.id.idTicket)).getText().toString());
        TicketWithProducts ticketWithProducts = AppDB.getTicketDao().getTicketWithProductsFromID(idTicket);

        Intent in = new Intent(this.context, EditTicketActivity.class);
        in.putExtra("ticket", ticketWithProducts);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
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
        TextView magasinView = convertView.findViewById(R.id.magasin);
        TextView productsView = convertView.findViewById(R.id.productName);
        TextView totalView = convertView.findViewById(R.id.totalTicket);

        imageView.setImageBitmap(dataModel.ticket.getImage());
        idView.setText(dataModel.ticket.ticket_id + "");
        magasinView.setText(dataModel.ticket.nom_magasin);
        productsView.setText(context.getResources().getQuantityString(R.plurals.products, dataModel.products.size(), dataModel.products.size()));
        totalView.setText(context.getString(R.string.total, dataModel.total()));

        return convertView;
    }
}
