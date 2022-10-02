package com.mtha.myguidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnShow, btnDate, btnTime;
    TextView tvDate, tvTime;
    AutoCompleteTextView autoText;
    int mHour, mMinute, mSecond;
    int mDate, mMonth, mYear;
    //khai bao va khoi tao datasource
    static final String[] cities ={"Ha Noi", "Hai Phong",
    "Nam Dinh", "Phu Tho", "Nha Trang"};
    //khai bao doi tuong progressbar Dialog
    ProgressDialog progressDialog;//khai bao
    Handler myHandler = new Handler();//khoi tao doi tuong handler de update gui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        btnShow = findViewById(R.id.btnShow);
        //xu ly su kien khi click vao btnShow thi hien thi progressbar dialog
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //xu ly su kien o day
                //thiet lap cac thuoc tinh cho progressdialog
                //khoi tao doi tuong progressDialog
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("ProgressDialog Example");
                progressDialog.setMessage("File downloading ...");
                progressDialog.setCancelable(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.setProgress(0);
                //goi phuong thuc show de hien thi progressDialog
                progressDialog.show();
                //tao mot luong thread de cap nhat lai gia tri thay doi tren progressbar
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //thay doi gia tri tren progressbar
                        try{
                            while(progressDialog.getProgress()<= progressDialog.getMax()){
                                //thuc hien cap nhat
                                Thread.sleep(1000);
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //cap nhat gui
                                        progressDialog.setProgress(progressDialog.getProgress()+1);
                                    }
                                });
                                if(progressDialog.getProgress()==progressDialog.getMax()){
                                    //ket thuc dialog va hien thong bao cho nguoi dung
                                    Toast.makeText(MainActivity.this, "Download done!",
                                            Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }

                        }catch (Exception e){
                            //xu ly ngoai le neu co loi
                            Toast.makeText(MainActivity.this, "ProgressDialog error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();//start luong thuc hien cong viec trong phuong thuc run

            }
        });

        autoText = findViewById(R.id.autoText);
        //khai bao va khoi tao doi tuong adapter de chua datasource
        ArrayAdapter<String> adapter
                =new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line,cities);
        //set adapter hien thi len autotext
        autoText.setAdapter(adapter);
    }

    private void getViews(){
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTimeSelect);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
    }

    public void onSelectTime(View v){
        //khoi tao doi tuong calendar de lay ra ngay gio hien tai
        final Calendar calendar = Calendar.getInstance();
        //lay ra thong tin ve gio, phut, giay hien tai
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        //khoi tao doi tuong TimePickerDialog de chon gio theo y muon
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        //set  thoi gian vao doi tuong tvTime
                        tvTime.setText(hour+":"+minute);
                    }
                },mHour,mMinute,true);
        timePickerDialog.show();
    }

    public void onDateSelect(View v){

    }
}