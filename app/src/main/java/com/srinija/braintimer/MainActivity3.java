package com.srinija.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String total = intent.getStringExtra("total");
        String right = intent.getStringExtra("right");

        Log.i("total" , total);
        Log.i("right" , right);

        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText("You scored "+right+" out of "+total);
    }

    public void restart(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}