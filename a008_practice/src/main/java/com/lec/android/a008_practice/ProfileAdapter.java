package com.lec.android.a008_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    static ProfileAdapter adapter;
    ArrayList<Profile> items = new ArrayList<Profile>();
    // Adapter 생성자
    public ProfileAdapter(){
        this.adapter=this;
    }


    //홀더 만들기...
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvAge,tvAdress;
        public ViewHolder(@NonNull View itemView) {   //아이템 레이아웃의 뷰 객체 전달댐
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvAge=itemView.findViewById(R.id.tvAge);
            tvAdress=itemView.findViewById(R.id.tvAdress);


        }
        public void setItem(Profile item){
            tvName.setText(item.getName());
            tvAdress.setText(item.getAdress());
            tvAge.setText(item.getAge());
        }
    }
}
