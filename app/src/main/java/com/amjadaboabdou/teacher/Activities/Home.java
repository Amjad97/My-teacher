package com.amjadaboabdou.teacher.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amjadaboabdou.teacher.Client.APIClient;
import com.amjadaboabdou.teacher.Interface.APIInterface;
import com.amjadaboabdou.teacher.Interface.ItemClickListener;
import com.amjadaboabdou.teacher.Models.Areas;
import com.amjadaboabdou.teacher.Models.Grade;
import com.amjadaboabdou.teacher.Models.ResponseModelUsers;
import com.amjadaboabdou.teacher.Models.User;
import com.amjadaboabdou.teacher.R;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener
        , OnMoreListener {

    SuperRecyclerView mRecyclerView;
    ImageView search;
    LinearLayout homelayout;

    String name,speciality,location,image,m,g,r;
    int id;
    StringBuilder grades = new StringBuilder();
    List<Areas> a;
    List<Grade> gr;

    SliderLayout mDemoSlider;

    APIInterface apiInterface;
    List<User> mDataList = new ArrayList<>();

    int selectedPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        mRecyclerView = findViewById(R.id.itemsRecyclerView);
        createAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setupMoreListener(this, 1);


        /**
         * GET USERS
         */
     //   getUsers(selectedPage);

        homelayout = findViewById(R.id.home_layout);
        homelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        search = findViewById(R.id.home_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Search.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Home.this,Login.class);
        startActivity(intent);
        finish();
    }

    private void createAdapter(SuperRecyclerView recyclerView) {
        final ParallaxRecyclerAdapter<User> adapter = new ParallaxRecyclerAdapter<User>(mDataList) {

            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<User> parallaxRecyclerAdapter, int position) {

                name = mDataList.get(position).getsName();
                speciality = mDataList.get(position).getsSpecilization();
                image = mDataList.get(position).getsAvatar();

                ((ViewHolder) viewHolder).teacher_name.setText("الاستاذ : "+name);
                ((ViewHolder) viewHolder).teacher_speciality.setText("التخصص : "+speciality);
                if (image.isEmpty()){
                    Picasso.with(getApplicationContext()).load(R.drawable.user).into(((ViewHolder) viewHolder).teacher_image);
                }else {
                    Picasso.with(getApplicationContext()).load(image).into(((ViewHolder) viewHolder).teacher_image);
                }

                ((ViewHolder) viewHolder).setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int p, boolean isLongClick) {
                        int position = p-1;
                        name = mDataList.get(position).getsName();
                        speciality = mDataList.get(position).getsSpecilization();
                        image = mDataList.get(position).getsAvatar();
                        m = mDataList.get(position).getsMobileNumber();
                        g = mDataList.get(position).getiGender();
                        a = mDataList.get(position).getAreas();
                        gr = mDataList.get(position).getGrades();
                        r = mDataList.get(position).getdRate();
                        id = mDataList.get(position).getPkIId();

                        for (Areas aa:a){
                            location = aa.getsName();
                        }

                        for (Grade aa:gr){
                            grades.append(aa.getsTitle()+", ");
                        }

                        Intent intent = new Intent(getApplicationContext(),Teacher_rating.class);
                        intent.putExtra("name","الاستاذ : "+name);
                        intent.putExtra("speciality",speciality);
                        intent.putExtra("image",image);
                        intent.putExtra("phone",m);
                        intent.putExtra("gender",g);
                        intent.putExtra("rate",r);
                        intent.putExtra("location",location);
                        intent.putExtra("grades",String.valueOf(grades));
                        intent.putExtra("uid",id);
                        startActivity(intent);
                        finish();
                    }
                });

            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<User> parallaxRecyclerAdapter, int i) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.row_item, viewGroup, false);
                return new ViewHolder(view,getApplicationContext(),mDataList);
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<User> parallaxRecyclerAdapter) {
                return mDataList.size();
            }
        };


        View header = getLayoutInflater().inflate(R.layout.header, recyclerView, false);
        mDemoSlider = header.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap();
        file_maps.put("",R.drawable.ic_background);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.stopAutoCycle();
        mDemoSlider.addOnPageChangeListener(this);

        adapter.setParallaxHeader(header, recyclerView);
        adapter.setData(mDataList);
   //     adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView teacher_name;
        TextView teacher_speciality;
        CircleImageView teacher_image;
        Context context;
        List<User> mDataList;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView,Context context , List<User> list) {
            super(itemView);

            this.context = context;
            this.mDataList = list;
            itemView.setOnClickListener(this);
            teacher_name = itemView.findViewById(R.id.teacher_name);
            teacher_speciality = itemView.findViewById(R.id.teacher_speciality);
            teacher_image = itemView.findViewById(R.id.teacher_image);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mRecyclerView.showMoreProgress();
        getUsers(selectedPage);
        selectedPage++;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    public void onSliderClick(BaseSliderView slider) {
        hideKeyboard();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void getUsers(int selectedPage){
        Call<ResponseModelUsers> call = apiInterface.getUsers(""+selectedPage);
        call.enqueue(new Callback<ResponseModelUsers>() {
            @Override
            public void onResponse(Call<ResponseModelUsers> call, Response<ResponseModelUsers> response) {
                ResponseModelUsers responseModelUsers = response.body();
                mDataList.addAll(responseModelUsers.getUsers());
                mRecyclerView.hideMoreProgress();
            }

            @Override
            public void onFailure(Call<ResponseModelUsers> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}