package com.example.dictionaryapp;

import android.content.Context;
import android.widget.Toast;

import com.example.dictionaryapp.Model.APIresponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/") // base url part of api
            .addConverterFactory(GsonConverterFactory.create())
            .build();

     // contructor for request manager
    public RequestManager(Context context) {
        this.context = context;
    }

    //interface for api calling

    public interface CallDictionary{
        @GET("entries/en/{word}")// end part of url
        //call for list of api response
        Call<List<APIresponse>> callMeanings(
                @Path("word") String word
        );
    }

    // method for handle our calling
    public void getWordMeaning(Onfetchdatalistener listener , String word) {
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<APIresponse>> call = callDictionary.callMeanings(word);


        // get the response

        try {
            call.enqueue(new Callback<List<APIresponse>>() {
                @Override
                public void onResponse(Call<List<APIresponse>> call, Response<List<APIresponse>> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(context , "Error" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onfetchdata(response.body().get(0) , response.message());//sent the first body of api response
                }

                @Override
                public void onFailure(Call<List<APIresponse>> call, Throwable t) {

                    listener.onError("request failed");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context , "an error" , Toast.LENGTH_SHORT).show();
        }
    }
}
