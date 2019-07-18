package com.amjadaboabdou.teacher;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseApplication extends Application {



    public  static Context mContext;
    public  static  ResponseModel mConstant;
    public  static String privacy;
    public static String rules;
    public static String search_page_word;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext =this;
        getConstants();
    }

    public static void  getConstants(){
        APIInterface  apiInterface = APIClient.getClient().create(APIInterface .class);
        Call<ResponseModel> call = apiInterface.getResponseModel();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                privacy =responseModel.getPrivacy();
                rules = responseModel.getRules();
                search_page_word = responseModel.getSearchPageWord();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
                privacy = "";
                rules = "";
                search_page_word = "";
            }
        });
    }

}
