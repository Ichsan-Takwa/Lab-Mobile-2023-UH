package com.example.driveku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driveku.databinding.ActivityFileDetailBinding;
import com.example.driveku.databinding.ActivityFilePreviewBinding;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    ArrayList<FileModel> listData;
    int Layout;
    Context context;

    public RecycleViewAdapter(Context context,ArrayList<FileModel> listData, int Layout) {
        this.listData = listData;
        this.Layout = Layout;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(Layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        FileModel data = listData.get(position);
        if (Layout == R.layout.activity_file_detail) {
            holder.bindDetail.filename.setText(data.fileName);
            holder.bindDetail.addedDate.setText(data.time);
            holder.bindDetail.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(context,Display.class);
                intent.putExtra("File",data);
                context.startActivity(intent);
            });
        } else if (Layout == R.layout.activity_file_preview) {
            holder.bindPreview.filename.setText(data.fileName);
            holder.bindPreview.profile.setImageURI(data.imageUri);
            holder.bindPreview.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(context,Display.class);
                intent.putExtra("File",data);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ActivityFileDetailBinding bindDetail;
        ActivityFilePreviewBinding bindPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            if (Layout == R.layout.activity_file_detail) {
                bindDetail = ActivityFileDetailBinding.bind(itemView);
            } else if (Layout == R.layout.activity_file_preview) {
                bindPreview = ActivityFilePreviewBinding.bind(itemView);
            }
        }
    }
}
