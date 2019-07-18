package com.amjadaboabdou.teacher.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Interface.ItemClickListener;
import com.amjadaboabdou.teacher.Models.City;
import com.amjadaboabdou.teacher.Models.Country;
import com.amjadaboabdou.teacher.Models.Grade;
import com.amjadaboabdou.teacher.Models.MainGrade;
import com.amjadaboabdou.teacher.Models.ResponseModel;
import com.amjadaboabdou.teacher.Models.ResponseModelGrades;
import com.amjadaboabdou.teacher.Models.User;
import com.amjadaboabdou.teacher.R;
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Signup extends AppCompatActivity {

    EditText speciality,user_name,user_phone,user_identity_number;
    ImageView add,select_image;
    RecyclerView subject_list;
    Toolbar mToolbar;
    Button mSignUp;
    ImageView back;
    Bitmap bitmap;
    LinearLayout region_layout, maingrades_layout, grades_layout;
    TextView region_text, maingrades_text, grades_text;
    CircleImageView user_image;
    AwesomeSpinner my_spinner;

    MyAdapter adapter;
    List<String> mSubjectList;
    StudyLevelSearchAdapter studyLevelSearchAdapter;
    StudyLevelSelectSearchAdapter studyLevelSelectSearchAdapter;
    RegionSearchAdapter regionSearchAdapter;

    AlertDialog alertDialog;

    List<String> countries = new ArrayList<>();
    List<String> mainGrades = new ArrayList<>();
    List<String> grades1 = new ArrayList<>();
    List<String> grades2= new ArrayList<>();
    List<String> grades3 = new ArrayList<>();
    List<String> grades4 = new ArrayList<>();

    List<City> cities;
    Integer city_id = null;

    APIInterface apiInterface;

    private static final int GALLERY_PICK = 1;
    String i_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        mToolbar = findViewById(R.id.teacher__signup_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        back = findViewById(R.id.teacher_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_identity_number = findViewById(R.id.user_identity_number);

        user_image = findViewById(R.id.user_image);
        select_image = findViewById(R.id.select_image);
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery_intent = new Intent();
                gallery_intent.setType("image/*");
                gallery_intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(gallery_intent,"SELECT IMAGE"),GALLERY_PICK);
            }
        });

        region_layout = findViewById(R.id.region_layout);
        maingrades_layout = findViewById(R.id.study_level_layout);
        grades_layout = findViewById(R.id.study_level_select_layout);

        region_text = findViewById(R.id.region_text);
        maingrades_text = findViewById(R.id.study_level_text);
        grades_text = findViewById(R.id.study_level_select_text);

        /**
         * GET COUNTRIES
         **/

        Call<ResponseModel> ResponseModelCall = apiInterface.getResponseModel();
        ResponseModelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                List<Country> country = responseModel.getCountries();
                for (Country c : country){
                    cities = c.getCities();
                    for (City cc : cities){
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
                AlertDialog.Builder alert=new AlertDialog.Builder(Teacher_Signup.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom,null);
                ImageView cancel = view.findViewById(R.id.dialog_search_cancel_btn);
                TextView title = view.findViewById(R.id.dialog_search_title_tv);
                RecyclerView list = view.findViewById(R.id.search_dialog_recycleview);

                regionSearchAdapter = new RegionSearchAdapter(getApplicationContext(),countries);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(regionSearchAdapter);

                title.setText("قم بتحديد المنطقة");

                alert.setView(view);

                alertDialog=alert.create();
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
                AlertDialog.Builder alert=new AlertDialog.Builder(Teacher_Signup.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom,null);
                ImageView cancel = view.findViewById(R.id.dialog_search_cancel_btn);
                TextView title = view.findViewById(R.id.dialog_search_title_tv);
                RecyclerView list = view.findViewById(R.id.search_dialog_recycleview);

                studyLevelSearchAdapter = new StudyLevelSearchAdapter(getApplicationContext(),mainGrades);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(studyLevelSearchAdapter);

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
                AlertDialog.Builder alert=new AlertDialog.Builder(Teacher_Signup.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_search_custom,null);
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

                studyLevelSelectSearchAdapter = new StudyLevelSelectSearchAdapter(getApplicationContext(),grades);
                list.setHasFixedSize(true);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                list.setAdapter(studyLevelSelectSearchAdapter);

                title.setText("قم بتحديد الفرع");

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

        speciality = findViewById(R.id.teacher_speciality);
        add = findViewById(R.id.teacher_add);

        mSubjectList = new ArrayList<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = speciality.getText().toString();
                if (!TextUtils.isEmpty(text)){
                    speciality.setText("");
                    mSubjectList.add(text);
                    adapter.notifyDataSetChanged();
                    hideKeyboard();
                }
            }
        });

        adapter = new MyAdapter(this,mSubjectList);
        subject_list = findViewById(R.id.teacher_subject_list);
        subject_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        subject_list.setHasFixedSize(true);
        subject_list.setAdapter(adapter);

        mSignUp = findViewById(R.id.teacher_signup);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_name.getText().toString().isEmpty()){
                    user_name.setError("هذا الحقل مطلوب");
                }else if (user_phone.getText().toString().isEmpty()){
                    user_phone.setError("هذا الحقل مطلوب");
                }else if (user_identity_number.getText().toString().isEmpty()){
                    user_identity_number.setError("هذا الحقل مطلوب");
                }else if (my_spinner.getSelectedItem() == null){
                    Toast.makeText(getApplicationContext(),"النوع مطلوب",Toast.LENGTH_LONG).show();
                } else if (region_text.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"المنطقة مطلوبة",Toast.LENGTH_LONG).show();
                }else if (maingrades_text.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"المرحلة الدراسية مطلوبة",Toast.LENGTH_LONG).show();
                }else if (grades_text.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"المرحلة الفرعية مطلوبة",Toast.LENGTH_LONG).show();
                }else if (mSubjectList.size() == 0){
                    Toast.makeText(getApplicationContext(),"التخصص مطلوب",Toast.LENGTH_LONG).show();
                }else{

                    String subjects = "";

                    for (String s :mSubjectList){
                        subjects = subjects+s+" , ";
                    }


                    RequestBody avatar = RequestBody.create(MultipartBody.FORM,"");

                    Call<User> userCall = apiInterface.postUser(user_name.getText().toString(),user_phone.getText().toString(),i_gender,
                            user_identity_number.getText().toString(),city_id+"","",subjects.substring(0,subjects.length()-2),avatar,"");
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            AlertDialog.Builder alert=new AlertDialog.Builder(Teacher_Signup.this);
                            View view = getLayoutInflater().inflate(R.layout.pop_layout,null);
                            Button pay = view.findViewById(R.id.pop_payment);
                            pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(),Payment_fees.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            alert.setView(view);
                            AlertDialog alertDialog=alert.create();
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.show();
                            alertDialog.setCanceledOnTouchOutside(false);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

                }
            }
        });

        my_spinner = findViewById(R.id.spinner);
        List<String> categories = new ArrayList();
        categories.add("معلم");
        categories.add("معلمة");

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        my_spinner.setAdapter(categoriesAdapter);

        my_spinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                if (position == 0){
                    i_gender = "1";
                }else if (position == 1){
                    i_gender = "2";
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(getApplicationContext()).load(resultUri).into(user_image);
            }
        }
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Teacher_Signup.this,Login.class);
        startActivity(intent);
        finish();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        Context context;
        List<String> mDataList;

        public MyAdapter(Context context,List<String> mDataList) {
            this.mDataList = mDataList;
            this.context = context;
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.subject_layout, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String subject_name = mDataList.get(position);
            holder.subject_name.setText(subject_name);

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    mDataList.remove(mDataList.get(position));
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            Button subject_name;
            ItemClickListener itemClickListener;

            public ViewHolder(View itemView) {
                super(itemView);
                subject_name = itemView.findViewById(R.id.subject_name);
                itemView.setOnClickListener(this);

            }

            public void setItemClickListener(ItemClickListener itemClickListener) {
                this.itemClickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                itemClickListener.onClick(view,getAdapterPosition(),false);
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

            holder.search_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    region_text.setText(mDataList.get(position));

                    for (City cc : cities){
                        if (cc.getsName().equals(region_text.getText())){
                            city_id = cc.getPkIId();
                        }
                    }
                    alertDialog.cancel();
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

    public class StudyLevelSelectSearchAdapter extends RecyclerView.Adapter<StudyLevelSelectSearchAdapter.ViewHolder>{

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
}
