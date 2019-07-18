package com.amjadaboabdou.teacher.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.BaseApplication;
import com.amjadaboabdou.teacher.R;

public class Login extends BaseActivity {

    Button teacher,student;
    TextView contact,privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (isNetworkAvailable()){
            BaseApplication.getConstants();
        }

        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
                    Intent intent = new Intent(Login.this,Teacher_Signup.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_SHORT).show();
                }
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(Login.this, Search.class);
                    startActivity(intent);
                    finish();
                }else {
                Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_SHORT).show();
                }
            }
        });

        contact = findViewById(R.id.contact);
        privacy = findViewById(R.id.privacy);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(Login.this, ContactActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_SHORT).show();
                }
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(Login.this, PrivacyActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_SHORT).show();
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
}
