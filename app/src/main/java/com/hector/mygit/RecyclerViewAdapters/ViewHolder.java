package com.hector.mygit.RecyclerViewAdapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.mygit.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView folderName;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        folderName = itemView.findViewById(R.id.folderName);

    }
}
