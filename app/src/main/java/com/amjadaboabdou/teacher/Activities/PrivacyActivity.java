package com.amjadaboabdou.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amjadaboabdou.teacher.BaseApplication;
import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.R;

public class PrivacyActivity extends BaseActivity {

    ImageView back;
    TextView privacy,rules;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        BaseApplication.getConstants();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        back = findViewById(R.id.privacy_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        privacy = findViewById(R.id.privacy);
        rules = findViewById(R.id.rules);

        privacy.setText(BaseApplication.privacy);
        rules.setText(BaseApplication.rules);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PrivacyActivity.this,Login.class);
        startActivity(intent);
        finish();
    }
}
