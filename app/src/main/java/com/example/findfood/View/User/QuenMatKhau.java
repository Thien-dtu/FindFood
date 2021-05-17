package com.example.findfood.View.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findfood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMatKhau extends AppCompatActivity implements View.OnClickListener {

    EditText EmailKhoiPhuc;
    Button btnKhoiPhuc;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        /*-------------------- Gán giá trị --------------------*/
        EmailKhoiPhuc = findViewById(R.id.EmailKhoiPhuc);
        btnKhoiPhuc = findViewById(R.id.btnKhoiPhuc);
        /*-------------------- END gán giá trị --------------------*/

        firebaseAuth =FirebaseAuth.getInstance();

        btnKhoiPhuc.setOnClickListener(this);
    }

    private boolean CheckEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnKhoiPhuc:
                String email = EmailKhoiPhuc.getText().toString().trim();
                boolean checkEmail = CheckEmail(email);
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(QuenMatKhau.this,getString(R.string.thogbaoguiemailkhoiphucthanhcong),Toast.LENGTH_SHORT).show();
                        }
                    });
                if (checkEmail) {

                }else {
                    Toast.makeText(QuenMatKhau.this,getString(R.string.thongbaokhongdungdinhdangmatkhau),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}