package com.example.task4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {

    ArrayList<FileModel> listData;
    int layout;
    Context context;

    public AdapterRV(ArrayList<FileModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO : Ke 1 buat kerangka / tentukan layout yang akan ditempati datanya
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        // tentukan kerangka layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRV.ViewHolder holder, int position) {
        // TODO : Ke 3 lekatkan data ke layout
        final FileModel data = this.listData.get(position);
        holder.tv_fileName.setText(data.fileName);
        holder.tv_addedDate.setText(data.addedDate);
        holder.iv_photo.setImageURI(data.fileUri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("namaFile", data.fileName);
                intent.putExtra("foto", data.fileUri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // TODO 4 : berikan ukuran datasource // list datanya
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fileName, tv_addedDate;
        ImageView iv_photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_fileName = itemView.findViewById(R.id.tv_fileName);
            tv_addedDate = itemView.findViewById(R.id.tv_addedDate);
            iv_photo = itemView.findViewById(R.id.iv_photo);
        }
    }
}
