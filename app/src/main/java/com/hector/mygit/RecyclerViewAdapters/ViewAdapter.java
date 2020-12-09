package com.hector.mygit.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.mygit.DataModels.RepoDataModel;
import com.hector.mygit.R;
import com.hector.mygit.RepoDetails;

import java.io.Serializable;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<RepoDataModel> repoDataModelList;

    public ViewAdapter(Context context, List<RepoDataModel> repoDataModelList) {
        this.context = context;
        this.repoDataModelList = repoDataModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.folderName.setText(repoDataModelList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, RepoDetails.class);
                i.putExtra("repoDetails", (Serializable) repoDataModelList.get(position));
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return repoDataModelList.size();
    }
}
