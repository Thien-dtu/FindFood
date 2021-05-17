package com.example.findfood.View.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

//import com.example.findfood.DangNhapActivity;
//import com.example.findfood.HelperClasses.UsersAdapter;
import com.example.findfood.DangNhapActivity;
import com.example.findfood.R;
import com.example.findfood.model.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrangCaNhan extends AppCompatActivity {

    Button btnSignOut, btnThemDuLieu, btnUpdate;
    ListView lvUsers;

    FirebaseAuth fAuth;
    FirebaseDatabase fData;
//    DatabaseReference dataRe;
//    UsersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);
//        addControls();

        btnSignOut = findViewById(R.id.btnOut);
        btnThemDuLieu =findViewById(R.id.btnThemDuLieu);
        btnUpdate = findViewById(R.id.btnUpdate);
//        lvUsers=findViewById(R.id.lvUsers);
//        lvUsers.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();

        btnThemDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), ThemActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), CapNhapProfile.class));
            }
        });

         btnSignOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 LoginManager.getInstance().logOut();
                 startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                 finish();
             }
         });
    }



//    private void addControls() {
//        lvUsers=findViewById(R.id.lvUsers);
//        adapter=new UsersAdapter(this,R.layout.listuser);
//        lvUsers.setAdapter(adapter);
//    }
}