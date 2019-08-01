package com.algorepublic.brandmaker.ui.stores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.algorepublic.brandmaker.R;
import com.algorepublic.brandmaker.model.StoresModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class AdaptorRVStore extends RecyclerView.Adapter<AdaptorRVStore.ViewHolder> {

    private ArrayList<StoresModel> data;
    private OnListItemClickedListener listener;
    private OnListItemLongClickedListener listenerLong;
    private Context context;
    private ArrayList<StoresModel> items;
    private ItemFilter mFilter = new ItemFilter();


    public interface OnListItemClickedListener {
        public void OnReply(int position, String data);
    }


    public interface OnListItemLongClickedListener {
        public void OnReply(int position, String data);
    }

    public AdaptorRVStore(ArrayList<StoresModel> data, Context context) {
        this.data = data;
        this.context = context;
        items=data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView;
        itemLayoutView = LayoutInflater.from(context).inflate(R.layout.item_checklist, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder vh, final int position) {
//    vh.tv_title.setText(data.get(position).getStoreName());
//    vh.tv_des.setText(data.get(position).getStoreFormat());
}


    public void setOnListItemClickedListener(OnListItemClickedListener listener) {
        this.listener = listener;
    }

    public void setOnListItemLongClickedListener(OnListItemLongClickedListener listenerLong) {
        this.listenerLong = listenerLong;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_des,tv_location,tv_distance;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tv_title=itemLayoutView.findViewById(R.id.tv_title);
            tv_des=itemLayoutView.findViewById(R.id.tv_des);
            tv_location=itemLayoutView.findViewById(R.id.tv_location);
            tv_distance=itemLayoutView.findViewById(R.id.tv_distance);
        }
    }

    // Return the size of your data (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 30;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            int count = items.size();
            final List<StoresModel> tempItems = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                if (items.get(i) != null) {
                    String name = items.get(i).getStoreName();
                    if (name.toLowerCase().contains(filterString)) {
                        tempItems.add(items.get(i));
                    }
                }
            }

            results.values = tempItems;
            results.count = tempItems.size();

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (ArrayList<StoresModel>) results.values;
            notifyDataSetChanged();
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

}