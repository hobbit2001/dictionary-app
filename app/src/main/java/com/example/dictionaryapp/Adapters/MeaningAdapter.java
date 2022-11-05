package com.example.dictionaryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.Model.Meanings;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.ViewHolders.MeaningViewHolder;
import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {

    private Context context;
    protected List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meaning_lis_items , parent , false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.textView_partsofspeech.setText("parts of speech" + meaningsList.get(position).getPartOfSpeech());
        holder.Recycler_defination.setHasFixedSize(true);
        holder.Recycler_defination.setLayoutManager(new GridLayoutManager(context , 1));
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context , meaningsList.get(position).getDefinitions());
        holder.Recycler_defination.setAdapter(definitionAdapter);

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(meaningsList.get(position).getSynonyms());
        antonyms.append(meaningsList.get(position).getAntonyms());


        holder.Textview_synonyms.setText(synonyms);
        holder.Textview_antonyms.setText(antonyms);

        holder.Textview_synonyms.setSelected(true);
        holder.Textview_antonyms.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
