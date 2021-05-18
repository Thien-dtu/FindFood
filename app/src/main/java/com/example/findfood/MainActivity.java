package com.example.findfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.findfood.CallBack.CategoriesCallBack;
import com.example.findfood.CallBack.FoodCallBack;
import com.example.findfood.Databases.DatabaseCategories;
import com.example.findfood.Databases.DatabaseFood;
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
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    Button btnTrangCaNhan;


//    RecyclerView recyclerView,recyclerView1;
//    ArrayList<MainModel> mainModels;
//    MainAdapter mainAdapter;
//    ArrayList<MainModel1> mainModel1s;
//    MainAdapter1 mainAdapter1;
    public static String emailuser = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.imageSlider);
        rcvhome = findViewById(R.id.rcvhome);
        rcvmonan = findViewById(R.id.rcvmonan);
        txtslogan = findViewById(R.id.txtslogan);
        databaseCategories = new DatabaseCategories(getApplicationContext());
        datacategories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(datacategories,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvhome.setLayoutManager(linearLayoutManager);
        rcvhome.setHasFixedSize(true);
        rcvhome.setAdapter(categoryAdapter);

        btnTrangCaNhan = findViewById(R.id.btnTrangCaNhan);

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


        btnTrangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangCaNhan.class);
                v.getContext().startActivity(intent);
            }
        });


//        recyclerView = findViewById(R.id.rcv);
//        Integer[] langLogo = {R.drawable.family,R.drawable.km50,R.drawable.km129,R.drawable.freegacoca,R.drawable.voucher99k};
//
//        mainModels = new ArrayList<>();
//        for (int i=0;i<langLogo.length;i++){
//            MainModel model = new MainModel(langLogo[i]);
//            mainModels.add(model);
//        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);

//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        mainAdapter = new MainAdapter(MainActivity.this,mainModels);
//        recyclerView.setAdapter(mainAdapter);


        //mon an quanh ban

//        recyclerView1 = findViewById(R.id.rcv_1);
//        String [] tenQuanList = {"Trà Sữa Bông","Bonpas Bakery","Cơm gà Duyên","Bún bò Huế"};
//        String [] diaChiQuanList = {"234 Núi Thành","02 Xô Viết Nghệ Tĩnh","128 Hoàng Diệu","30 Lý Thài Tổ"};
//        mainModel1s = new ArrayList<>();

//        for (int i=0;i<tenQuanList.length;i++){
//            MainModel1 model1 = new MainModel1(tenQuanList[i],diaChiQuanList[i]);
//            mainModel1s.add(model1);
//        }
//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
//
//        recyclerView1.setLayoutManager(layoutManager1);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());
//
//        mainAdapter1 = new MainAdapter1(MainActivity.this,mainModel1s);
//        recyclerView1.setAdapter(mainAdapter1);

        Intent intent = getIntent();
        emailuser = intent.getStringExtra("email");
        return ;
    }

    public void renewItems(SliderView sliderView) {
        List<ModelItem> sliderItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ModelItem sliderItem = new ModelItem();
            if (i % 2 == 0) {
                sliderItem.setImageurl("https://photos.app.goo.gl/nHs9bM3CsrDnezTe9");
            } else {
                sliderItem.setImageurl("https://photos.app.goo.gl/GGaLWwT9NaZYhpmD7");
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
        sliderItem.setImageurl("https://photos.app.goo.gl/bgrTzSKTsTsGZxa87");
        adapter.addItem(sliderItem);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}