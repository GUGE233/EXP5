package com.example.guge.exp3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {

    static List<information> list_cart = new ArrayList<>(100);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if(list_cart.size()==0){
            list_cart.add(new information("*",123,"价格","购物车","123"));
        }


//        final information info = (information) getIntent().getSerializableExtra("Info_to_cart");
//        if(info!=null){
//            list_cart.add(new information(info.getFirstletter(),info.getPicture(),info.getPrice(),info.getName(),info.getMessage()));
//        }
        ListView listView = (ListView)findViewById(R.id.cart_list);
        final MyAdapter myAdapter = new MyAdapter(cart.this,list_cart);
        listView.setAdapter(myAdapter);



        //浮动按钮相关
        final FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.FAB);
        floatingActionButton.setImageResource(R.drawable.shoplist);
        final Intent intent1 = new Intent(this,MainActivity.class);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setImageResource(R.drawable.mainpage);
                startActivity(intent1);
            }
        });

        //购物车列表相关
        final Intent intent2 = new Intent(cart.this,good_info.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    information ifo_to_info = list_cart.get(i);
                    intent2.putExtra("Info", ifo_to_info);
                    startActivity(intent2);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int a, long l) {
                if(a!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(cart.this);
                    builder.setTitle("移除商品");
                    builder.setMessage("从购物车中移除" + list_cart.get(a).getName() + "？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            list_cart.remove(a);
                            myAdapter.notifyDataSetChanged();

                        }

                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                    return true;

            }
        });




    }
}
