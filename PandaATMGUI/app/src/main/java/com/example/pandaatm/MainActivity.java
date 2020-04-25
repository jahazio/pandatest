package com.example.pandaatm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //gets current time and date
        TextView datetime = findViewById(R.id.dateTxt);
        Date timenow = Calendar.getInstance().getTime();
        datetime.setText(timenow.toString());

        //transition from splash screen to main screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, mainScreen.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

        //map to login
        ImageButton atm1Button = findViewById(R.id.bldg1ATM);
        ImageButton atm2Button = findViewById(R.id.bldg17ATM);
        ImageButton atm3Button = findViewById(R.id.mktplaceATM);
        ImageButton atm4Button = findViewById(R.id.bscATM);
        ImageButton atm5Button = findViewById(R.id.libATM);

        atm1Button.setOnClickListener(this);
        atm2Button.setOnClickListener(this);
        atm3Button.setOnClickListener(this);
        atm4Button.setOnClickListener(this);
        atm5Button.setOnClickListener(this);

        //login to transaction/welcome
        Button clrButton = findViewById(R.id.clearButton);
        clrButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bldg1ATM:
            case R.id.bldg17ATM:
            case R.id.mktplaceATM:
            case R.id.bscATM:
            case R.id.libATM:
                Intent intent = new Intent(MainActivity.this, loginScreen.class);
                startActivity(intent);
                break;
            case R.id.clearButton:
                Intent intent1 = new Intent(MainActivity.this, transactionScreenn.class);
                startActivity(intent1);
                break;

        }
    }
}
