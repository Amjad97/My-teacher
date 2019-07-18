package com.amjadaboabdou.teacher.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Models.Rate;
import com.amjadaboabdou.teacher.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_rating extends AppCompatActivity {

    TextView name, phone, gender, location, grade, speciality;
    ImageView back;
    CircleImageView imageView;
    RatingBar user_rate;
    Button rating;
    String image;
    int id;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_rating);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        String s = intent.getStringExtra("name");
        String ss = intent.getStringExtra("speciality");
        image = intent.getStringExtra("image");
        id = intent.getIntExtra("id",0);
        final String p = intent.getStringExtra("phone");
        String g = intent.getStringExtra("gender");
        String r = intent.getStringExtra("rate");
        String l = intent.getStringExtra("location");
        String gr = intent.getStringExtra("grades");
        String temp_gr = gr.substring(0, gr.length() - 2);

        imageView = findViewById(R.id.user_image);
        user_rate = findViewById(R.id.user_rate);
        name = findViewById(R.id.teacher_name);
        phone = findViewById(R.id.teacher_phone);
        gender = findViewById(R.id.teacher_gender);
        location = findViewById(R.id.teacher_location);
        grade = findViewById(R.id.teacher_grade);
        speciality = findViewById(R.id.teacher_speciality);

        name.setText(s);
        speciality.setText(ss);
        user_rate.setRating(Float.valueOf(r));
        location.setText("المملكة العربية السعودية - " + l);
        grade.setText(temp_gr);
        if (g.equals("1")) {
            gender.setText("معلم");
        } else {
            gender.setText("معلمة");
        }
        if (!image.isEmpty()) {
            Picasso.with(this).load(image).into(imageView);
        }
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+p));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }

            }
        });

        back = findViewById(R.id.rating_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_rating.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        rating = findViewById(R.id.rating);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(Teacher_rating.this);
                View view = getLayoutInflater().inflate(R.layout.pop_rating_layouy,null);
                Button rate = view.findViewById(R.id.pop_rate);
                CircleImageView user_image = view.findViewById(R.id.user_image_rate);
                TextView username = view.findViewById(R.id.user_name_rate);
                final RatingBar bar = view.findViewById(R.id.user_rate_bar);

                if (!image.isEmpty()) {
                    Picasso.with(getApplicationContext()).load(image).into(user_image);
                }
                username.setText(name.getText());

                alert.setView(view);
                final AlertDialog alertDialog=alert.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                rate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float rateing = bar.getRating();
                        Rate rate = new Rate(""+id,""+rateing);
                        Call<Rate> call = apiInterface.postRate(rate);
                        call.enqueue(new Callback<Rate>() {
                            @Override
                            public void onResponse(Call<Rate> call, Response<Rate> response) {
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Rate> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Teacher_rating.this,Home.class);
        startActivity(intent);
        finish();
    }
}
