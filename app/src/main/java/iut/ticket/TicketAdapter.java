package iut.ticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import iut.ticket.dao.AppDB;
import iut.ticket.dao.TicketWithProducts;

public class TicketAdapter extends ArrayAdapter<TicketWithProducts> {

    private final ListTicketActivity activity;

    public TicketAdapter(ListTicketActivity activity) {
        super(activity, R.layout.history_list_view, AppDB.getTicketDao().getTicketWithProducts());
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TicketWithProducts dataModel = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_list_view, parent, false);
            convertView.setOnClickListener(this.activity);
        }

        ImageView imageView = convertView.findViewById(R.id.ticketPhoto);
        TextView idView = convertView.findViewById(R.id.idTicket);
        TextView magasinView = convertView.findViewById(R.id.magasin);
        TextView productsView = convertView.findViewById(R.id.productName);
        TextView totalView = convertView.findViewById(R.id.totalTicket);

        imageView.setImageBitmap(dataModel.ticket.getImage());
        idView.setText(dataModel.ticket.ticket_id + "");
        magasinView.setText(dataModel.ticket.nom_magasin);
        productsView.setText(activity.getResources().getQuantityString(R.plurals.products, dataModel.products.size(), dataModel.products.size()));
        totalView.setText(activity.getString(R.string.total, dataModel.total()));

        return convertView;
    }
}
