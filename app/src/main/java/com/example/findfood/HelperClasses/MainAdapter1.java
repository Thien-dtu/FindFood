package com.example.findfood.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findfood.model.MainModel1;
import com.example.findfood.R;

import java.util.ArrayList;

public class MainAdapter1 extends RecyclerView.Adapter<MainAdapter1.ViewHolder> {

    ArrayList<MainModel1> mainModel1s;
    Context context;

    public  MainAdapter1(Context context, ArrayList<MainModel1> mainModel1s){
        this.context = context;
        this.mainModel1s = mainModel1s;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tenquan.setText(mainModel1s.get(position).getTenquan());
        holder.diachi.setText(mainModel1s.get(position).getDiachi());

    }

    @Override
    public int getItemCount() {
        return mainModel1s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenquan,diachi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tenquan = itemView.findViewById(R.id.tenquan);
            diachi = itemView.findViewById(R.id.diachi);
        }
    }
}
