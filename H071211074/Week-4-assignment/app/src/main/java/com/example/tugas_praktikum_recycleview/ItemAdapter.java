package com.example.tugas_praktikum_recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_praktikum_recycleview.databinding.ItemGrid1Binding;
import com.example.tugas_praktikum_recycleview.databinding.ItemGrid2Binding;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<ItemModel> listData;
    int layout;
    static OnItemListener onItemListener;

    public ItemAdapter (ArrayList<ItemModel> listData, int layout) {
        this.listData = listData;
        this.layout = layout;
    }
    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO 1 : buat kerangka / tentukan layout yang akan ditempati datanya
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout, parent, false);

        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO 3 : letakkan data ke layout
        if (layout == R.layout.item_grid1) {
            ItemModel data = listData.get(position);
            holder.bind1.ivFile.setImageURI(data.getPhoto());
            holder.bind1.tvFilename.setText(data.filename);
            holder.bind1.getRoot().setOnClickListener(view -> {onItemListener.onItemClick(position);});
        } else {
            ItemModel data = listData.get(position);
            holder.bind2.tvFilename.setText(data.getFilename());
            holder.bind2.tvDate.setText(data.getAddedDate());
            holder.bind2.getRoot().setOnClickListener(view -> {onItemListener.onItemClick(position);});
        }
    }

    @Override
    public int getItemCount() {
        // TODO 4 : berikan ukuran datasource / list datanya
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemGrid1Binding bind1;
        ItemGrid2Binding bind2;;
        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            // TODO 2 : Inisialisasi elemen elemen dalam layout
            super(itemView);
            if (layout == R.layout.item_grid1) {
                bind1 = ItemGrid1Binding.bind(itemView);
            } else {
                bind2 = ItemGrid2Binding.bind(itemView);
            }
        }

    }
    interface OnItemListener {
        void onItemClick(int position);
    }
}
