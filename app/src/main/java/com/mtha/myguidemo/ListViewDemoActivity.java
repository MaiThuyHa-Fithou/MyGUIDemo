package com.mtha.myguidemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewDemoActivity extends AppCompatActivity {
    ListView lvContact;
    //dinh nghia datasource
    static final String[] contacts={"ha 987654", "minh 87654","tuan 9876543"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        lvContact = findViewById(R.id.lvContact);
        //tao adapter chua datasource va layout item
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(ListViewDemoActivity.this,
                        android.R.layout.simple_list_item_1,contacts);
        //set adapter cho listview de hien thi
        lvContact.setAdapter(adapter);
        //xu ly su kien onItemClick tren tung item cua listview
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long l) {
                //lay ra duoc gia tri cua item dang click
                String contact =(String) lvContact.getItemAtPosition(position);
                Toast.makeText(ListViewDemoActivity.this,
                        "contact["+contact+"]",
                        Toast.LENGTH_LONG).show();
            }
        });
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,
                                           View view, int position, long l) {
                String name = lvContact.getItemAtPosition(position).toString();
                //tao ra dialog thong bao cho nguoi dung
                AlertDialog.Builder builder  =
                        new AlertDialog.Builder(ListViewDemoActivity.this);
                builder.setMessage("Xin chao " + name)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //xu ly su kien la gi
                                //vi du xoa 1 item tren listview
                                //dong alertDialog lai
                                finish();

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //xu ly su kien cho nut cancel
                                //vi du huy k xoa item tren listview
                                dialogInterface.cancel();
                            }
                        });
                //tao alertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }
}