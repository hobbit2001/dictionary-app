package com.example.dictionaryapp.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {

    public TextView TextView_Definition , TextView_Example ;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);

        TextView_Definition = itemView.findViewById(R.id.TextView_Definition);
        TextView_Example = itemView.findViewById(R.id.TextView_Example);

    }
}
