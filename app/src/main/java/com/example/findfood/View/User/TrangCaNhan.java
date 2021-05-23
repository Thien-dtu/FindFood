package com.example.findfood.View.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.findfood.DangNhapActivity;
//import com.example.findfood.HelperClasses.UsersAdapter;
import com.example.findfood.CallBack.UserCallBack;
import com.example.findfood.DangNhapActivity;
import com.example.findfood.Databases.DatabaseUser;
import com.example.findfood.EditProfile;
import com.example.findfood.MessegerActivity;
import com.example.findfood.R;
import com.example.findfood.model.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangCaNhan extends AppCompatActivity{
    private Switch darkModeSwitch;
    RelativeLayout edtEditProfile;
    ImageView profileCircleImageView;
    TextView usernameTextView, email, logout,history,txteditprofile,txtchangepassword,map;
    DatabaseUser databaseUser;
    FirebaseUser firebaseUser;

    FirebaseAuth fAuth;
    FirebaseDatabase fData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);

        edtEditProfile = findViewById(R.id.edtEditProfile);
        profileCircleImageView = findViewById(R.id.profileCircleImageView);
        usernameTextView = findViewById(R.id.usernameTextView);
        email= findViewById(R.id.email);
        logout = findViewById(R.id.txtlogout);
        txtchangepassword = findViewById(R.id.txtchangepassword);
        map = findViewById(R.id.map);
        txteditprofile = findViewById(R.id.txteditprofile);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        history = findViewById(R.id.history);
        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();
        databaseUser = new DatabaseUser(getApplicationContext());
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseUser.getAll(new UserCallBack() {
            @Override
            public void onSuccess(ArrayList<User> lists) {
                for (int i =0 ; i<lists.size();i++){
                    if (lists.get(i).getToken()!=null && lists.get(i).getToken().equalsIgnoreCase(firebaseUser.getUid())){
                        email.setText(lists.get(i).getEmail());
                        usernameTextView.setText(lists.get(i).getName());
                        Picasso.get()
                                .load(lists.get(i).getImage()).into(profileCircleImageView);
                    }
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), "Đang phát triển, dự kiến khả dụng : CDIO 4", Toast.LENGTH_SHORT).show();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                Toast.makeText(getApplicationContext(), "Đang phát triển, dự kiến khả dụng : CDIO 4", Toast.LENGTH_SHORT).show();
            }
        });

        edtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(iProfile);
            }
        });

        txteditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(iProfile);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                finish();
            }
        });


    }
}