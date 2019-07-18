package com.amjadaboabdou.teacher.Interface;

import com.amjadaboabdou.teacher.Models.Pay;
import com.amjadaboabdou.teacher.Models.Rate;
import com.amjadaboabdou.teacher.Models.ResponseModel;
import com.amjadaboabdou.teacher.Models.ResponseModelGrades;
import com.amjadaboabdou.teacher.Models.ResponseModelUsers;
import com.amjadaboabdou.teacher.Models.Suggest;
import com.amjadaboabdou.teacher.Models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("constants/list")
    Call<ResponseModel> getResponseModel();

    @GET("grades/list")
    Call<ResponseModelGrades> getResponseModelsGrades();

    @GET("users/list")
    Call<ResponseModelUsers> getUsers(@Query("i_page_number") String i_page_number);

    @POST("users/register")
    @Multipart
    Call<User> postUser(@Part("s_username") String user_name, @Part("s_mobile_number") String mobile_number,
                        @Part("i_gender") String i_gender, @Part("s_identification_number") String identification_number,
                        @Part("s_area") String area, @Part("s_grade") String grade, @Part("s_specilization") String specilization
            , @Part("s_avatar") RequestBody avatar, @Part("s_udid") String udid);

    @POST("users/pay")
    Call<Pay> postPay(@Body Pay pay);

    @POST("users/suggest")
    Call<Suggest> postSuggest(@Body Suggest suggest);

    @POST("users/rate")
    Call<Rate> postRate(@Body Rate rate);

}
