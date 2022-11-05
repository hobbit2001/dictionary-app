package com.example.dictionaryapp.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_partsofspeech;
    public RecyclerView Recycler_defination;
    public TextView Textview_synonyms ,Textview_antonyms;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partsofspeech = itemView.findViewById(R.id.textView_partsofspeech);
        Recycler_defination = itemView.findViewById(R.id.Recycler_defination);
        Textview_synonyms = itemView.findViewById(R.id.Textview_synonyms);
        Textview_antonyms = itemView.findViewById(R.id.Textview_antonyms);
    }
}
