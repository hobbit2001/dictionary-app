package com.example.dictionaryapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.Adapters.MeaningAdapter;
import com.example.dictionaryapp.Adapters.PhoneticsAdapter;
import com.example.dictionaryapp.Model.APIresponse;

public class  MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView word;
    RecyclerView phonetics , meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise the view

        searchView = findViewById(R.id.search_view);
        word = findViewById(R.id.textview_word);
        phonetics = findViewById(R.id.recyclerphonetics);
        meanings = findViewById(R.id.recycler_meannings);
        progressDialog = new ProgressDialog(this );

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager =  new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener , "clever");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("fetching response for" + query);
                progressDialog.show();
                RequestManager manager =  new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener , query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private final Onfetchdatalistener listener =  new Onfetchdatalistener() {
        @Override
        public void onfetchdata(APIresponse apIresponse, String msg) {
            progressDialog.dismiss();
            if(apIresponse == null){
                Toast.makeText(MainActivity.this , "No data found", Toast.LENGTH_SHORT).show();
            }
            showData(apIresponse);
        }

        @Override
        public void onError(String message) {

            progressDialog.dismiss();
            Toast.makeText(MainActivity.this , message , Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(APIresponse apIresponse) {

        word.setText("Words: "+ apIresponse.getWord());
        phonetics.setHasFixedSize(true);
        phonetics.setLayoutManager(new GridLayoutManager(this , 1));
        phoneticsAdapter = new PhoneticsAdapter(this , apIresponse.getPhonetics());
        phonetics.setAdapter(phoneticsAdapter);

        meanings.setHasFixedSize(true);
        meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this ,apIresponse.getMeanings());
        meanings.setAdapter(meaningAdapter);
    }


}