package iut.ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import iut.ticket.dao.TicketWithProducts;

public class TicketAdapter extends ArrayAdapter<TicketWithProducts> /*implements View.OnClickListener*/{

    private List<TicketWithProducts> dataSet;
    Context mContext;

    public TicketAdapter(List<TicketWithProducts> data, Context context) {
        super(context, R.layout.history_list_view, data);
        this.dataSet = data;
        this.mContext = context;
    }

    /*@Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel= (DataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }*/

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TicketWithProducts dataModel = getItem(position);
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_list_view, parent, false);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getType());
        viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

}
