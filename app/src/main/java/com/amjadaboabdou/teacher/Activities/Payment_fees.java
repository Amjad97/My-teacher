package com.amjadaboabdou.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Models.Pay;
import com.amjadaboabdou.teacher.R;
import com.isapanah.awesomespinner.AwesomeSpinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment_fees extends AppCompatActivity {

    ImageView back;
    Button payment;
    AwesomeSpinner spinner;
    EditText name,transfer_num;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_fees);

        apiInterface = APIClient.getClient().create(APIInterface.class);


        spinner = findViewById(R.id.BankAccount);
        name = findViewById(R.id.name);
        transfer_num = findViewById(R.id.transfer_num);

        back = findViewById(R.id.payment_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_fees.this,Teacher_Signup.class);
                startActivity(intent);
                finish();
            }
        });

        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pay pay = new Pay("",name.getText().toString(),transfer_num.getText().toString());
                Call<Pay> call = apiInterface.postPay(pay);
                call.enqueue(new Callback<Pay>() {
                    @Override
                    public void onResponse(Call<Pay> call, Response<Pay> response) {
                        Intent intent = new Intent(Payment_fees.this,Home.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Pay> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Payment_fees.this,Teacher_Signup.class);
        startActivity(intent);
        finish();
    }
}