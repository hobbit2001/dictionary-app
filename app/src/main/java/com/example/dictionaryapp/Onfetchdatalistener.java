package com.example.dictionaryapp;

import com.example.dictionaryapp.Model.APIresponse;

public interface Onfetchdatalistener {

    void onfetchdata(APIresponse apIresponse , String msg);
    void onError(String message);

}
