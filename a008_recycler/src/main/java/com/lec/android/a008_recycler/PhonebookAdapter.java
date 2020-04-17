package com.lec.android.a008_recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//어댑터 객체정의
//데이터 를 받아서 각 아이템별로 뷰를 생성
public class PhonebookAdapter extends RecyclerView.Adapter<PhonebookAdapter.ViewHolder> {


    // Adapter 객체 정의
// 데이터(Phonebook) 를 받아서 각 Item 별로 View 를 생성하는 역할
// Adapter는 리스트에서 다룰 데이터가 필요하다
// Adapter가 데이터에 연결되어야 하는 것은 사실이나, 데이터를 Adapter를 직접 다룰지
// 아니면 별도의 데이터 관리는 따로 하는 구조로 만들지는 선택의 몫
// 본 예제에서는 Adapter 안에 직접 데이터를 다루어보겠습니다
    ArrayList<Phonebook> items = new ArrayList<Phonebook>();

    static PhonebookAdapter adapter;

    // Adapter 생성자
    public PhonebookAdapter(){
        this.adapter=this;
    }


    // onCreateViewHolder() : ViewHolder 가 생성될때 호출됨
    // 각 item 을 위해 정의한 레이아웃(ex:XML) 으로 View 객체를 만들어 줍니다.
    // 이들 View객체를 새로 만들 ViewHolder 에 담아 리턴.
    //
    //  'View  타입' 을 위한 정수값이 매개변수로 넘겨진 --> viewType
    //    이를 통해 아이템별로 View를 다양하게 표현 가능.  (ListView 에는 없던 개선점)
    //    예를들면, 각각의 'View 타입' 별로 따로따로 XML레이아웃을 달리 하여 보여줄수 있는 겁니다.
    //    * 그러나, 일반적으로는 한가지만 운용함.*
    //
    //  매개변수로 전달된 ViewGroup 객체는 각 아이템을 위한 객체
    //  이로부터 Context 객체를 뽑아내어 Layout inflation 을 해야 한다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //주어진 뷰그룹으로부터의 LayoutInflater추출
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //준비된 레아아웃으로 부터 뷰를 만들어 뷰그룹에 장착
        //그렇게 만들어진 뷰를 리턴턴
        View itemView=inflater.inflate(R.layout.item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // onBindViewHolder() : ViewHolder 가 '재사용' 될때 호출됨
    // View 객체는 그대로 기존것을 사용 (이것이 재사용!) 하고 데이터만 바꾸어 주면 됨.
    //  이전에 이미 만들어진. 재활용할수 있는 ViewHolder 객체  와
    //  리스트 상에 몇번째 데이터인지에 대한 정보 (position) 가 넘어온다

        Phonebook item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
    // getItemCount() : 어댑터에서 다루는 현시점 아이템(데이터)의 개수
    // Selection Widget 에서 수시로 getItemCount() 를 호출하여 뷰를 업데이트 한다

        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPhoto;
        TextView tvName,tvPhone,tvEmail;
        ImageButton btnDelItem;
        Switch swOnOff;
        public ViewHolder(@NonNull View itemView) {   //아이템 레이아웃의 뷰 객체 전달댐
            super(itemView);

            ivPhoto=itemView.findViewById(R.id.ivPhoto);
            tvName=itemView.findViewById(R.id.tvName);
            tvPhone=itemView.findViewById(R.id.tvPhone);
            tvEmail=itemView.findViewById(R.id.tvEmail);
            swOnOff=itemView.findViewById(R.id.swOnOff);
            btnDelItem=itemView.findViewById(R.id.btnDelItem);

            //스위치 누르면 이벤트
            swOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        tvPhone.setVisibility(View.INVISIBLE);
                        tvEmail.setVisibility(View.INVISIBLE);
                    }else{
                        tvPhone.setVisibility(View.VISIBLE);
                        tvEmail.setVisibility(View.VISIBLE);
                    }
                }
            });
            //삭제버튼시
            btnDelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //포지션정보필요
                     //어댑터로 부터 데이터 삭제도 진행되어야한다.
                    adapter.removeItem(getAdapterPosition());
                    //데이터변경내역이 adapter에 반영되어야한다.
                    adapter.notifyDataSetChanged();
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition(); //이 리스너가 장착된 아이템의 position
                    //Toast.makeText(v.getContext(),"position"+position,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(),PhonebookDetail.class);

                    intent.putExtra("pb",adapter.getItem(position));

                    v.getContext().startActivity(intent);

                }
            });

        }
        public void setItem(Phonebook item){
            ivPhoto.setImageResource(item.getPhoto());
            tvName.setText(item.getName());
            tvPhone.setText(item.getPhone());
            tvEmail.setText(item.getEmail());
        }
    }
    public void addItem(Phonebook item) {  items.add(item); }
    public void addItem(int position, Phonebook item) {   items.add(position, item);}
    public void setItems(ArrayList<Phonebook> items) {   this.items = items;}
    public Phonebook getItem(int position) {   return items.get(position);}
    public void setItem(int position, Phonebook item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }

}
