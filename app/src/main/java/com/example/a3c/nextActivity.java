package com.example.a3c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class nextActivity<assetManager, player> extends AppCompatActivity {
private Button btn1;
    Button Btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btn1=(Button) findViewById(R.id.sss);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(nextActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }

}