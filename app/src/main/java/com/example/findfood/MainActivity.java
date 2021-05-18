package com.example.findfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.findfood.HelperClasses.MainAdapter;
import com.example.findfood.HelperClasses.MainAdapter1;
import com.example.findfood.View.User.TrangCaNhan;
import com.example.findfood.model.MainModel;
import com.example.findfood.model.MainModel1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;
    ArrayList<MainModel1> mainModel1s;
    MainAdapter1 mainAdapter1;
    public static String emailuser = "";

    Button btnTrangCaNhan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date currentTime = Calendar.getInstance().getTime();
        if (currentTime.getHours() <12) {

        }

        btnTrangCaNhan = findViewById(R.id.btnTrangCaNhan);
        btnTrangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangCaNhan.class);
                v.getContext().startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rcv);
        Integer[] langLogo = {R.drawable.family,R.drawable.km50,R.drawable.km129,R.drawable.freegacoca,R.drawable.voucher99k};

        mainModels = new ArrayList<>();
        for (int i=0;i<langLogo.length;i++){
            MainModel model = new MainModel(langLogo[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdapter = new MainAdapter(MainActivity.this,mainModels);
        recyclerView.setAdapter(mainAdapter);


        //mon an quanh ban

        recyclerView1 = findViewById(R.id.rcv_1);
        String [] tenQuanList = {"Trà Sữa Bông","Bonpas Bakery","Cơm gà Duyên","Bún bò Huế"};
        String [] diaChiQuanList = {"234 Núi Thành","02 Xô Viết Nghệ Tĩnh","128 Hoàng Diệu","30 Lý Thài Tổ"};
        mainModel1s = new ArrayList<>();

        for (int i=0;i<tenQuanList.length;i++){
            MainModel1 model1 = new MainModel1(tenQuanList[i],diaChiQuanList[i]);
            mainModel1s.add(model1);
        }
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        mainAdapter1 = new MainAdapter1(MainActivity.this,mainModel1s);
        recyclerView1.setAdapter(mainAdapter1);

        Intent intent = getIntent();
        emailuser = intent.getStringExtra("email");
    }
}