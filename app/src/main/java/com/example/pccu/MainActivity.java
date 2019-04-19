package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibtnDownload=null;
    private Button btnLogin; //登入按鈕


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得控制項物件
        initViews();

        //取消ActionBar
        getSupportActionBar().hide();
        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




    private void initViews()
    {
        btnLogin = (Button)findViewById(R.id.bt);
        ibtnDownload=(ImageButton)super.findViewById(R.id.imageButton);
        ibtnDownload.setOnClickListener(new DownloadOnClickListener1());
        ibtnDownload=(ImageButton)super.findViewById(R.id.imageButton2);
        ibtnDownload.setOnClickListener(new DownloadOnClickListener2());
        ibtnDownload=(ImageButton)super.findViewById(R.id.imageButton3);
        ibtnDownload.setOnClickListener(new DownloadOnClickListener3());
    }


    //按下 登入 的結果
    public void login(View view){
        Intent it = new Intent(MainActivity.this, Signin.class);
        startActivity(it);
        finish();
    }

    //按下 租屋搜尋的結果
    private class DownloadOnClickListener1 implements OnClickListener{
        public void onClick(View v){
                Intent it = new Intent(MainActivity.this, Search.class);
                startActivity(it);
                finish();
        }
    }

    //按下 即時通訊 的結果
    private class DownloadOnClickListener2 implements OnClickListener{
        public void onClick(View view){
            Toast.makeText(getApplicationContext(),"聊天",Toast.LENGTH_SHORT).show();
        }
    }

    //按下 預約租屋 的結果
    private class DownloadOnClickListener3 implements OnClickListener{
        public void onClick(View view){
            Toast.makeText(getApplicationContext(),"預約看屋",Toast.LENGTH_SHORT).show();
        }
    }



}

