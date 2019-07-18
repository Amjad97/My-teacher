package com.amjadaboabdou.teacher.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.BaseApplication;
import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Models.City;
import com.amjadaboabdou.teacher.Models.Country;
import com.amjadaboabdou.teacher.Models.Grade;
import com.amjadaboabdou.teacher.Models.MainGrade;
import com.amjadaboabdou.teacher.Models.ResponseModel;
import com.amjadaboabdou.teacher.Models.ResponseModelGrades;
import com.amjadaboabdou.teacher.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    ImageView back;
    Button search;
    TextView teacher_m,teacher_w,search_page_word;

    LinearLayout region_layout, maingrades_layout, grades_layout;
    TextView region_text, maingrades_text, grades_text;

    StudyLevelSearchAdapter mSearchAdapter;
    StudyLevelSelectSearchAdapter mSearchAdapter_;
    RegionSearchAdapter mRegionSearchAdapter;
    List<String> countries = new ArrayList<>();
    List<String> mainGrades = new ArrayList<>();
    List<String> grades1 = new ArrayList<>();
    List<String> grades2= new ArrayList<>();
    List<String> grades3 = new ArrayList<>();
    List<String> grades4 = new ArrayList<>();

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        BaseApplication.getConstants();

        back = findViewById(R.id.search_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        teacher_m = findViewById(R.id.teacher_m);
        teacher_w = findViewById(R.id.teacher_w);

        teacher_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher_m.setBackgroundResource(R.drawable.bu_bg2);
                teacher_m.setTextColor(Color.WHITE);

                teacher_w.setBackgroundResource(0);
                teacher_w.setTextColor(Color.parseColor("#b3b3b6"));
            }
        });

        teacher_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher_w.setBackgroundResource(R.drawable.bu_bg2);
                teacher_w.setTextColor(Color.WHITE);

                teacher_m.setBackgroundResource(0);
                teacher_m.setTextColor(Color.parseColor("#b3b3b6"));
            }
        });


        region_layout = findViewById(R.id.search_region_layout);
        maingrades_layout = findViewById(R.id.search_level_layout);
        grades_layout = findViewById(R.id.search_level_select_layout);

        search_page_word = findViewById(R.id.search_page_word);
        region_text = findViewById(R.id.search_region_text);
        maingrades_text = findViewById(R.id.search_level_text);
        grades_text = findViewById(R.id.search_level_select_text);

        search_page_word.setText(BaseApplication.search_page_word);

        /**
         * GET COUNTRIES
         **/

        Call<ResponseModel> call = apiInterface.getResponseModel();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                List<Country> country = responseModel.getCountries();
                for (Country c : country) {
                    List<City> cities = c.getCities();
                    for (City cc : cities) {
                        countries.add(cc.getsName());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * GET GRADES
         **/

        Call<ResponseModelGrades> ResponseCall = apiInterface.getResponseModelsGrades();
        ResponseCall.enqueue(new Callback<ResponseModelGrades>() {
            @Override
            public void onResponse(Call<ResponseModelGrades> call, Response<ResponseModelGrades> response) {
                ResponseModelGrades responsemodels = response.body();
                List<MainGrade> list = responsemodels.getMainGrades();
                for (MainGrade m:list){
                    mainGrades.add(m.getsTitle());
                    if (m.getPkIId() == 1){
                        List<Grade> list1 = m.getGrades();
                        for (Grade g :list1){
                            grades1.add(g.getsTitle());
                        }
                    }else if (m.getPkIId() == 2){
                        List<Grade> list1 = m.getGrades();
                        for (Grade g :list1){
                            grades2.add(g.getsTitle());
                        }
                    }else if (m.getPkIId() == 14){
                        List<Grade> list1 = m.getGrades();
                        for (Grade g :list1){
                            grades3.add(g.getsTitle());
                        }
                    }else if (m.getPkIId() == 118){
                        List<Grade> list1 = m.getGrades();
                        for (Grade g :list1){
                            grades4.add(g.getsTitle());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelGrades> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        region_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(Search.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom,null);
                ImageView cancel = view.findViewById(R.id.dialog_search_cancel_btn);
                TextView title = view.findViewById(R.id.dialog_search_title_tv);
                RecyclerView list = view.findViewById(R.id.search_dialog_recycleview);

                mRegionSearchAdapter = new RegionSearchAdapter(getApplicationContext(),countries);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(mRegionSearchAdapter);

                title.setText("قم بتحديد المنطقة");

                alert.setView(view);

                final AlertDialog alertDialog=alert.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
            }
        });

        maingrades_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(Search.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom,null);
                ImageView cancel = view.findViewById(R.id.dialog_search_cancel_btn);
                TextView title = view.findViewById(R.id.dialog_search_title_tv);
                RecyclerView list = view.findViewById(R.id.search_dialog_recycleview);

                mSearchAdapter = new StudyLevelSearchAdapter(getApplicationContext(),mainGrades);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(mSearchAdapter);

                title.setText("قم بتحديد المرحلة");

                alert.setView(view);

                final AlertDialog alertDialog=alert.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
            }
        });

        grades_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Search.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom, null);
                ImageView cancel = view.findViewById(R.id.dialog_search_cancel_btn);
                TextView title = view.findViewById(R.id.dialog_search_title_tv);
                RecyclerView list = view.findViewById(R.id.search_dialog_recycleview);

                List<String> grades = new ArrayList<>();
                String s = maingrades_text.getText().toString();
                if (s.contains("ثانوي")){
                    for (String ss:grades1){
                        grades.add(ss);
                    }
                }

                if (s.contains("متوسط")){
                    for (String ss:grades2){
                        grades.add(ss);
                    }
                }

                if (s.contains("ابتدائي")){
                    for (String ss:grades3){
                        grades.add(ss);
                    }
                }

                if (s.contains("جامعي")){
                    for (String ss:grades4){
                        grades.add(ss);
                    }
                }
                mSearchAdapter_ = new StudyLevelSelectSearchAdapter(getApplicationContext(), grades);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(mSearchAdapter_);

                title.setText("قم بتحديد الفرع");

                alert.setView(view);

                final AlertDialog alertDialog = alert.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
            }
        });

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable()){
                    Toast.makeText(getApplicationContext(),"لا يوجد اتصال بالانترنت",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(Search.this, Home.class);
                    startActivity(intent);
                    finish();
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
        Intent intent = new Intent(Search.this,Login.class);
        startActivity(intent);
        finish();
    }

    public class StudyLevelSearchAdapter extends RecyclerView.Adapter<StudyLevelSearchAdapter.ViewHolder> {

        Context context;
        List<String> mDataList;

        public StudyLevelSearchAdapter(Context context, List<String> mDataList) {
            this.mDataList = mDataList;
            this.context = context;
        }

        @NonNull
        @Override
        public StudyLevelSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.search_row_item, parent, false);
            return new StudyLevelSearchAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            String search_text = mDataList.get(position);
            holder.search_text.setText(search_text);

            if (position == mDataList.size()-1){
                holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
            }

            if (maingrades_text.getText().toString().contains(mDataList.get(position))){
                if (position == mDataList.size()-1){
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                }else {
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            holder.search_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position == mDataList.size()-1){
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
                            if (maingrades_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    maingrades_text.getText().toString().contains(","+mDataList.get(position))){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (maingrades_text.getText().toString().contains(mDataList.get(position)+",")){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                            maingrades_text.append(","+mDataList.get(position));
                            if (maingrades_text.getText().toString().substring(0,1).equals(",")){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(maingrades_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }else{
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundColor(Color.WHITE);
                            if (maingrades_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    maingrades_text.getText().toString().contains(","+mDataList.get(position))){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (maingrades_text.getText().toString().contains(mDataList.get(position)+",")){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            maingrades_text.append(","+mDataList.get(position));
                            if (maingrades_text.getText().toString().substring(0,1).equals(",")){
                                maingrades_text.setText(maingrades_text.getText().toString().
                                        replace(maingrades_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView search_check;
            TextView search_text;
            LinearLayout search_layout;

            public ViewHolder(View itemView) {
                super(itemView);
                search_check = itemView.findViewById(R.id.search_item_check);
                search_text = itemView.findViewById(R.id.search_item_text);
                search_layout = itemView.findViewById(R.id.search_layout);
            }
        }
    }

    public class StudyLevelSelectSearchAdapter extends RecyclerView.Adapter<StudyLevelSelectSearchAdapter.ViewHolder> {

        Context context;
        List<String> mDataList;

        public StudyLevelSelectSearchAdapter(Context context, List<String> mDataList) {
            this.mDataList = mDataList;
            this.context = context;
        }

        @NonNull
        @Override
        public StudyLevelSelectSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.search_row_item, parent, false);
            return new StudyLevelSelectSearchAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            String search_text = mDataList.get(position);
            holder.search_text.setText(search_text);

            if (position == mDataList.size()-1){
                holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
            }

            if (grades_text.getText().toString().contains(mDataList.get(position))){
                if (position == mDataList.size()-1){
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                }else {
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            holder.search_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position == mDataList.size()-1){
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
                            if (grades_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    grades_text.getText().toString().contains(","+mDataList.get(position))){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (grades_text.getText().toString().contains(mDataList.get(position)+",")){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                grades_text.setText(grades_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                            grades_text.append(","+mDataList.get(position));
                            if (grades_text.getText().toString().substring(0,1).equals(",")){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(grades_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }else{
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundColor(Color.WHITE);
                            if (grades_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    grades_text.getText().toString().contains(","+mDataList.get(position))){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (grades_text.getText().toString().contains(mDataList.get(position)+",")){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                grades_text.setText(grades_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            grades_text.append(","+mDataList.get(position));
                            if (grades_text.getText().toString().substring(0,1).equals(",")){
                                grades_text.setText(grades_text.getText().toString().
                                        replace(grades_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView search_check;
            TextView search_text;
            LinearLayout search_layout;

            public ViewHolder(View itemView) {
                super(itemView);
                search_check = itemView.findViewById(R.id.search_item_check);
                search_text = itemView.findViewById(R.id.search_item_text);
                search_layout = itemView.findViewById(R.id.search_layout);
            }
        }
    }

    public class RegionSearchAdapter extends RecyclerView.Adapter<RegionSearchAdapter.ViewHolder> {

        Context context;
        List<String> mDataList;

        public RegionSearchAdapter(Context context, List<String> mDataList) {
            this.mDataList = mDataList;
            this.context = context;
        }

        @NonNull
        @Override
        public RegionSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.search_row_item, parent, false);
            return new RegionSearchAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            String search_text = mDataList.get(position);
            holder.search_text.setText(search_text);

            if (position == mDataList.size()-1){
                holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
            }

            if (region_text.getText().toString().contains(mDataList.get(position))){
                if (position == mDataList.size()-1){
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                }else {
                    holder.search_check.setBackgroundResource(R.drawable.select_bg);
                    holder.search_text.setTextColor(Color.WHITE);
                    holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            holder.search_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position == mDataList.size()-1){
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_unselect_bg);
                            if (region_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    region_text.getText().toString().contains(","+mDataList.get(position))){
                                region_text.setText(region_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (region_text.getText().toString().contains(mDataList.get(position)+",")){
                                region_text.setText(region_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                region_text.setText(region_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundResource(R.drawable.last_item_select_bg);
                            region_text.append(","+mDataList.get(position));
                            if (region_text.getText().toString().substring(0,1).equals(",")){
                                region_text.setText(region_text.getText().toString().
                                        replace(region_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }else{
                        if (holder.search_text.getTextColors().getDefaultColor() == Color.WHITE){
                            holder.search_check.setBackgroundResource(R.drawable.un_select_bg);
                            holder.search_text.setTextColor(Color.parseColor("#6d6d6d"));
                            holder.search_layout.setBackgroundColor(Color.WHITE);
                            if (region_text.getText().toString().contains(","+mDataList.get(position)+",") ||
                                    region_text.getText().toString().contains(","+mDataList.get(position))){
                                region_text.setText(region_text.getText().toString().
                                        replace(","+mDataList.get(position),""));
                            }else if (region_text.getText().toString().contains(mDataList.get(position)+",")){
                                region_text.setText(region_text.getText().toString().
                                        replace(mDataList.get(position)+",",""));
                            }else{
                                region_text.setText(region_text.getText().toString().
                                        replace(mDataList.get(position),""));
                            }
                        }else {
                            holder.search_check.setBackgroundResource(R.drawable.select_bg);
                            holder.search_text.setTextColor(Color.WHITE);
                            holder.search_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            region_text.append(","+mDataList.get(position));
                            if (region_text.getText().toString().substring(0,1).equals(",")){
                                region_text.setText(region_text.getText().toString().
                                        replace(region_text.getText().toString().substring(0,1),""));
                            }
                        }
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView search_check;
            TextView search_text;
            LinearLayout search_layout;

            public ViewHolder(View itemView) {
                super(itemView);
                search_check = itemView.findViewById(R.id.search_item_check);
                search_text = itemView.findViewById(R.id.search_item_text);
                search_layout = itemView.findViewById(R.id.search_layout);
            }
        }
    }

}