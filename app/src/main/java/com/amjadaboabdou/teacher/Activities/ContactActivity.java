package com.amjadaboabdou.teacher.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Models.Suggest;
import com.amjadaboabdou.teacher.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {

    ImageView back;
    EditText contact_name,contact_phone,contact_identity_number,contact_msg;
    Button send;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        back = findViewById(R.id.contact_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        contact_name = findViewById(R.id.contact_name);
        contact_phone = findViewById(R.id.contact_phone);
        contact_identity_number = findViewById(R.id.contact_identity_number);
        contact_msg = findViewById(R.id.contact_message);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable()){
                    Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_LONG).show();
                }else if (contact_name.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"الاسم مطلوب",Toast.LENGTH_LONG).show();
                }else if (contact_phone.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"رقم الجوال مطلوب",Toast.LENGTH_LONG).show();
                }else if (contact_identity_number.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"رقم الهوية/الاقامة مطلوبة",Toast.LENGTH_LONG).show();
                }else if (contact_msg.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"الرسالة فارغة !!",Toast.LENGTH_LONG).show();
                }else {

                    Suggest suggest = new Suggest(contact_phone.getText().toString(),contact_msg.getText().toString(),
                            contact_name.getText().toString(),contact_identity_number.getText().toString());
                    Call<Suggest> call = apiInterface.postSuggest(suggest);
                    call.enqueue(new Callback<Suggest>() {
                        @Override
                        public void onResponse(Call<Suggest> call, Response<Suggest> response) {
                            Toast.makeText(getApplicationContext(),"تم ارسال الرسالة بنجاح",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ContactActivity.this,Login.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Suggest> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContactActivity.this,Login.class);
        startActivity(intent);
        finish();
    }
}
