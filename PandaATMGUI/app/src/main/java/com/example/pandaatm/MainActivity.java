package com.example.pandaatm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pandaatm.ui.login.loginScreen;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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


        //gets current time and date
        TextView datetime = findViewById(R.id.dateTxt);
        Date timenow = Calendar.getInstance().getTime();
        datetime.setText(timenow.toString());


        //if ATM on map is clicked, goes to login screen
        ImageButton atm1Button = findViewById(R.id.bldg1ATM);
        ImageButton atm2Button = findViewById(R.id.bldg17ATM);
        ImageButton atm3Button = findViewById(R.id.mktplaceATM);
        ImageButton atm4Button = findViewById(R.id.bscATM);
        ImageButton atm5Button = findViewById(R.id.libATM);
        final Intent intent = new Intent(MainActivity.this, loginScreen.class);

        atm1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        atm2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        atm3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        atm4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        atm5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //if login is clicked in login screen
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, transactionScreenn.class);
                startActivity(intent);
            }
        });

        //if clear is clicked in login screen
        Button clearButton = findViewById(R.id.clearButton); //atm login clear
        Button clearButton1 = findViewById(R.id.clearButton1); //balance screen clear
        Button clearButton2 = findViewById(R.id.clearButton2); //cash deposit screen clear
        Button clearButton3 = findViewById(R.id.clearButton3); //check deposit screen clear
        Button clearButton4 = findViewById(R.id.clearButton4); //outside transfer screen clear
        Button clearButton5 = findViewById(R.id.clearButton5); //withdraw different amt screen clear
        Button cancelButton = findViewById(R.id.cancelButton); //transaction screen cancel
        Button cancelButton1 = findViewById(R.id.cancelButton1); //withdraw screen cancel
        clearButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText cardNum = findViewById(R.id.cardNumberText);
                EditText pinNum = findViewById(R.id.pinText);
                cardNum.getText().clear();
                pinNum.getText().clear();
            }
        }

                ));
        clearButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText accType = findViewById(R.id.accountTypeTxt);
                @SuppressLint("WrongViewCast") EditText balTxt = findViewById(R.id.balanceText);
                accType.getText().clear();
                balTxt.getText().clear();
            }
        });
        clearButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText cashamt = findViewById(R.id.cashamt);
                cashamt.getText().clear();
            }
        });
        clearButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText checkamt = findViewById(R.id.checkamt);
                checkamt.getText().clear();
            }
        });
        clearButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText acctNum = findViewById(R.id.acctNum);
                EditText amtTrans = findViewById(R.id.amountTransfer);
                acctNum.getText().clear();
                amtTrans.getText().clear();
            }
        });
        clearButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText withdrawAmt = findViewById(R.id.withdrawAmt);
                withdrawAmt.getText().clear();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginScreen.class);
                startActivity(intent);
            }
        });
        cancelButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginScreen.class);
                startActivity(intent);
            }
        });
        //if back to map is clicked in login screen
        Button backMap = findViewById(R.id.backToMapButton);
        backMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, mainScreen.class);
                startActivity(intent);
            }
        });

        //if deposit is clicked in transaction screen
        Button depositButton = findViewById(R.id.depositBtn);
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, depositChoiceScreen.class);
                startActivity(intent);
            }
        });

        //if cash deposit is clicked in deposit choice screen
        Button cashDeposit = findViewById(R.id.cashDeposit);
        cashDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cashDepositScreen.class);
                startActivity(intent);
            }
        });

        //if enter is clicked in cash deposit screen
        Button enterCash = findViewById(R.id.button);
        enterCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, transactionScreenn.class);
                startActivity(intent);
            }
        });

        //if clear is clicked in cash deposit screen



        //if check deposit is checked in deposit balance screen
        Button checkDeposit = findViewById(R.id.checkDeposit);
        checkDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, checkDepositScreen.class);
                startActivity(intent);
            }
        });

        //if withdraw is clicked in transaction screen
        Button withdrawButton = findViewById(R.id.withdrawBtn);
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, withdrawScreen.class);
                startActivity(intent);
            }
        });

        // if transfer is clicked in transaction screen
        Button transferButton = findViewById(R.id.transferBtn);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, transferType.class);
                startActivity(intent);
            }
        });

        //if balance button is clicked in transaction screen
        Button balanceButton = findViewById(R.id.balanceBtn);
        balanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, balanceScreen.class);
                startActivity(intent);
            }
        });



    }




}





















