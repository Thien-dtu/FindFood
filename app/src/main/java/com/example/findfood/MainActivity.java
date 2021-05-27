package com.example.findfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findfood.CallBack.CategoriesCallBack;
import com.example.findfood.CallBack.FoodCallBack;
import com.example.findfood.CallBack.UserCallBack;
import com.example.findfood.Databases.DatabaseCategories;
import com.example.findfood.Databases.DatabaseFood;
import com.example.findfood.Databases.DatabaseUser;
import com.example.findfood.HelperClasses.AdapterViewPayer;
import com.example.findfood.HelperClasses.CategoryAdapter;
import com.example.findfood.HelperClasses.FoodAdapter;
import com.example.findfood.HelperClasses.MainAdapter;
import com.example.findfood.HelperClasses.MainAdapter1;
import com.example.findfood.HelperClasses.ModelItem;
import com.example.findfood.View.User.TrangCaNhan;
import com.example.findfood.model.Categories;
import com.example.findfood.model.Food;
import com.example.findfood.model.MainModel;
import com.example.findfood.model.MainModel1;
import com.example.findfood.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private AdapterViewPayer adapter;
    SliderView sliderView;
    EditText edtsearch;
    ArrayList<Categories> datacategories;
    DatabaseCategories databaseCategories;
    TextView txtslogan;
    CategoryAdapter categoryAdapter;
    FoodAdapter foodAdapter;
    ArrayList<Food> foodArrayList;
    DatabaseFood databaseFood;
    RecyclerView rcvhome,rcvmonan;


    FirebaseStorage storage;
    StorageReference storageReference;
    CircleImageView anhdaidien;
    DatabaseUser databaseUser;
    FirebaseUser firebaseUser;
    String anh;
    public static String emailuser = "";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sliderView = findViewById(R.id.imageSlider);
        rcvhome = findViewById(R.id.rcvhome);
        rcvmonan = findViewById(R.id.rcvmonan);
        txtslogan = findViewById(R.id.txtslogan);
        anhdaidien = findViewById(R.id.anhdaidien);
        databaseCategories = new DatabaseCategories(getApplicationContext());
        datacategories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(datacategories,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvhome.setLayoutManager(linearLayoutManager);
        rcvhome.setHasFixedSize(true);
        rcvhome.setAdapter(categoryAdapter);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseUser = new DatabaseUser(getApplicationContext());
        databaseUser.getAll(new UserCallBack() {
            @Override
            public void onSuccess(ArrayList<User> lists) {
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).getToken()!=null && lists.get(i).getToken().equalsIgnoreCase(firebaseUser.getUid())) {
                        anh = lists.get(i).getImage();
                    }
                }
                if (anh ==null){
                    Picasso.get().load("https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg").into(anhdaidien);
                }else if (anh !=null){
                    Picasso.get().load(anh).into(anhdaidien);
                }
            }

            @Override
            public void onError(String message) {

            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        if (currentTime.getHours() <12) {
            txtslogan.setText("Bạn Muốn Ăn Gì Sáng Nay ?");
            foodArrayList = new ArrayList<>();
            foodAdapter = new FoodAdapter(foodArrayList,getApplicationContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            rcvmonan.setLayoutManager(gridLayoutManager);
            rcvmonan.setHasFixedSize(true);
            rcvmonan.setAdapter(foodAdapter);
            databaseFood = new DatabaseFood(getApplicationContext());
            databaseFood.getAll(new FoodCallBack() {
                @Override
                public void onSuccess(ArrayList<Food> lists) {
                    foodArrayList.clear();
                    for (int i =0;i<lists.size();i++){
                        if (lists.get(i).getStatus().equalsIgnoreCase("Sáng")){
                            foodArrayList.add(lists.get(i));
                            foodAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onError(String message) {

                }
            });
        } else if (currentTime.getHours() <= 18 && currentTime.getHours() >= 14 ) {
            txtslogan.setText("Bạn Muốn Ăn Gì Chiều Nay ?");
            foodArrayList = new ArrayList<>();
            foodAdapter = new FoodAdapter(foodArrayList,getApplicationContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            rcvmonan.setLayoutManager(gridLayoutManager);
            rcvmonan.setHasFixedSize(true);
            rcvmonan.setAdapter(foodAdapter);
            databaseFood = new DatabaseFood(getApplicationContext());
            databaseFood.getAll(new FoodCallBack() {
                @Override
                public void onSuccess(ArrayList<Food> lists) {
                    foodArrayList.clear();
                    for (int i =0;i<lists.size();i++){
                        if (lists.get(i).getStatus().equalsIgnoreCase("Chiều")){
                            foodArrayList.add(lists.get(i));
                            foodAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onError(String message) {

                }
            });
        } else if (currentTime.getHours() > 18) {
            txtslogan.setText("Bạn Muốn Ăn Gì Tối Nay ?");
            foodArrayList = new ArrayList<>();
            foodAdapter = new FoodAdapter(foodArrayList,getApplicationContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            rcvmonan.setLayoutManager(gridLayoutManager);
            rcvmonan.setHasFixedSize(true);
            rcvmonan.setAdapter(foodAdapter);
            databaseFood = new DatabaseFood(getApplicationContext());
            databaseFood.getAll(new FoodCallBack() {
                @Override
                public void onSuccess(ArrayList<Food> lists) {
                    foodArrayList.clear();
                    for (int i =0;i<lists.size();i++){
                        if (lists.get(i).getStatus().equalsIgnoreCase("Tối")){
                            foodArrayList.add(lists.get(i));
                            foodAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onError(String message) {

                }
            });
        }
        databaseCategories.getAll(new CategoriesCallBack() {
            @Override
            public void onSuccess(ArrayList<Categories> lists) {
                datacategories.clear();
                datacategories.addAll(lists);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });

        sharedPreferences = getApplicationContext().getSharedPreferences("toado", Context.MODE_PRIVATE);

        adapter = new AdapterViewPayer(getApplicationContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        renewItems(sliderView);
        removeLastItem(sliderView);
        addNewItem(sliderView);


        anhdaidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangCaNhan.class);
                v.getContext().startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        Intent intent = getIntent();
        emailuser = intent.getStringExtra("email");
        return ;
    }

    public void renewItems(SliderView sliderView) {
        List<ModelItem> sliderItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ModelItem sliderItem = new ModelItem();
            switch (i) {
                case 0:
                    sliderItem.setImageurl("https://1.bp.blogspot.com/-1Q2XPYvxMag/YKOI0H3dA9I/AAAAAAAAAB4/ox70QTj9a4831YbPkZgWmk_roKqspC4ogCNcBGAsYHQ/s1747/family.png");
                    break;
                case 1:
                    sliderItem.setImageurl("https://1.bp.blogspot.com/-cZh16lRm7d4/YKOI0E2gbjI/AAAAAAAAAB0/iSFMyBJrUzsnu9dWpbK1FTWgr9CWcqqtACNcBGAsYHQ/s970/5.png");
                    break;
                case 2:
                    sliderItem.setImageurl("https://1.bp.blogspot.com/-wN9fUhMoGTo/YKOIzEN-hZI/AAAAAAAAABs/hHOiWywVYuEG5CenGgut4AMUyvuq1uk8ACNcBGAsYHQ/s970/2.png");
                    break;
                case 3:
                    sliderItem.setImageurl("https://1.bp.blogspot.com/-p-f9fqrBZ0w/YKOIzrCE-rI/AAAAAAAAABw/0CqSJS6QvQw2TWKUhtXpB6Q-2e0-HGwdgCNcBGAsYHQ/s970/4.png");
                    break;

            }
            sliderItemList.add(sliderItem);
        }
        adapter.ViewPagerAdapter(sliderItemList);
    }


    public void removeLastItem(SliderView sliderView) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteitem(adapter.getCount() - 1);
    }

    public void addNewItem(SliderView sliderView) {
        ModelItem sliderItem = new ModelItem();
        sliderItem.setImageurl("https://1.bp.blogspot.com/-W445RQ2YtbU/YKOI0sIDkzI/AAAAAAAAACA/7r2uGRJFKzkbNjG7i6mZbKaJTLzpNhKwQCNcBGAsYHQ/s970/giamgia.png");
        adapter.addItem(sliderItem);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}