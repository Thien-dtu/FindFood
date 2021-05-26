package com.example.findfood.Databases;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.findfood.CallBack.ChatUserStoreCallBack;
import com.example.findfood.model.ChatUserStore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseChatUserStore {
    Context context;
    DatabaseReference mRef;
    String key;

    public DatabaseChatUserStore(Context context) {
        this.context = context;
        this.mRef = FirebaseDatabase.getInstance().getReference("Chats");
    }
    public void getAll(final ChatUserStoreCallBack callback) {
        final ArrayList<ChatUserStore> dataloai = new ArrayList<>();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dataloai.clear();

                    for (DataSnapshot data : snapshot.getChildren()){
                        ChatUserStore categories = data.getValue(ChatUserStore.class);
                        dataloai.add(categories);
                    }
                    callback.onSuccess(dataloai);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.toString());
            }
        });
    }
    public void insert(ChatUserStore item){
        // push cây theo mã tự tạo
        // string key lấy mã push
        key = mRef.push().getKey();
        //insert theo child mã key setvalue theo item
        mRef.child(key).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Sign Up Thành Công", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Sign Up Thất Bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
