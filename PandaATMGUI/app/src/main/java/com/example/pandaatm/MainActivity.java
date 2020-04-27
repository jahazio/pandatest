package com.example.pandaatm;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static int SPLASH_TIME_OUT = 4000;
    Intent main = new Intent(this, MainActivity.class);
    Intent login = new Intent(this, loginScreen.class);
    Intent trans = new Intent(this, transactionScreenn.class);
    Intent cashDep = new Intent(this, cashDepositScreen.class);
    Intent checkDep = new Intent(this, checkDepositScreen.class);
    Intent depChoice = new Intent(this, depositChoiceScreen.class);
    Intent withdraw = new Intent(this, withdrawScreen.class);
    Intent withdrawDiff = new Intent(this, withdrawDifferentAmt.class);
    Intent transferType = new Intent(this, transferType.class);
    Intent outsideTransfer = new Intent(this, outsideTransfer.class);
    Intent balance = new Intent(this, balanceScreen.class);
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
        }, SPLASH_TIME_OUT);


        //gets current time and date
        TextView datetime = findViewById(R.id.dateTxt);
        Date timenow = Calendar.getInstance().getTime();
        datetime.setText(timenow.toString());


        ImageButton atm1Button = findViewById(R.id.bldg1ATM);
        ImageButton atm2Button = findViewById(R.id.bldg17ATM);
        ImageButton atm3Button = findViewById(R.id.mktplaceATM);
        ImageButton atm4Button = findViewById(R.id.bscATM);
        ImageButton atm5Button = findViewById(R.id.libATM);

        Button clearButton = findViewById(R.id.clearButton); //atm login clear
        Button clearButton1 = findViewById(R.id.clearButton1); //cash deposit screen clear
        Button clearButton2 = findViewById(R.id.clearButton2); //check deposit screen clear
        Button clearButton3 = findViewById(R.id.clearButton3); //outside transfer screen clear
        Button clearButton4 = findViewById(R.id.clearButton4); //withdraw different amt screen clear
        Button cancelButton = findViewById(R.id.cancelButton); //transaction screen cancel
        Button cancelButton1 = findViewById(R.id.cancelButton1); //withdraw screen cancel
        Button cancelButton2 = findViewById(R.id.cancelButton2); //balance screen cancel
        Button login = findViewById(R.id.loginButton);

        Button depositBtn = findViewById(R.id.depositBtn); //DEPOSIT PATH
        Button cashDeposit = findViewById(R.id.cashDeposit);
        Button checkDeposit = findViewById(R.id.checkDeposit);
        Button enterButton = findViewById(R.id.enterButton);
        Button enterButton1 = findViewById(R.id.enterButton1);
        Button previous = findViewById(R.id.previous);
        Button previous1 = findViewById(R.id.previous1);

        Button withdrawBtn = findViewById(R.id.withdrawBtn); //WITHDRAW PATH
        Button diffAmt = findViewById(R.id.diffAmt);
        Button enterButton2 = findViewById(R.id.enterButton2);

        Button transferBtn = findViewById(R.id.transferBtn); //TRANSFER PATH
        Button checkingAcct = findViewById(R.id.checkingAcct);
        Button savingAcct = findViewById(R.id.savingAcct);
        Button enterButton3 = findViewById(R.id.enterButton3);
        Button previous2 = findViewById(R.id.previous2);

        Button balanceBtn = findViewById(R.id.balanceBtn); //BALANCE PATH
        Button continueButton = findViewById(R.id.continueButton);

        atm1Button.setOnClickListener(this);
        atm2Button.setOnClickListener(this);
        atm3Button.setOnClickListener(this);
        atm4Button.setOnClickListener(this);
        atm5Button.setOnClickListener(this);

        clearButton.setOnClickListener(this);
        clearButton1.setOnClickListener(this);
        clearButton2.setOnClickListener(this);
        clearButton3.setOnClickListener(this);
        clearButton4.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        cancelButton1.setOnClickListener(this);
        cancelButton2.setOnClickListener(this);

        login.setOnClickListener(this);

        depositBtn.setOnClickListener(this);
        cashDeposit.setOnClickListener(this);
        checkDeposit.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        enterButton1.setOnClickListener(this);
        previous.setOnClickListener(this);
        previous1.setOnClickListener(this);

        withdrawBtn.setOnClickListener(this);
        diffAmt.setOnClickListener(this);
        enterButton2.setOnClickListener(this);

        transferBtn.setOnClickListener(this);
        checkingAcct.setOnClickListener(this);
        savingAcct.setOnClickListener(this);
        enterButton3.setOnClickListener(this);
        previous2.setOnClickListener(this);

        balanceBtn.setOnClickListener(this);
        continueButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bldg1ATM: //if ATM on map is clicked, goes to login screen
            case R.id.bldg17ATM:
            case R.id.mktplaceATM:
            case R.id.bscATM:
            case R.id.libATM:
                startActivity(login);
                break;
            case R.id.clearButton:
                EditText cardNum = findViewById(R.id.cardNumberText);
                EditText pinNum = findViewById(R.id.pinText);
                cardNum.getText().clear();
                pinNum.getText().clear();
                break;
            case R.id.clearButton1:
                EditText cashamt = findViewById(R.id.cashamt);
                cashamt.getText().clear();
                break;
            case R.id.clearButton2:
                EditText checkamt = findViewById(R.id.checkamt);
                checkamt.getText().clear();
                break;
            case R.id.clearButton3:
                EditText acctNum = findViewById(R.id.acctNum);
                EditText amtTrans = findViewById(R.id.amountTransfer);
                acctNum.getText().clear();
                amtTrans.getText().clear();
                break;
            case R.id.clearButton4:
                EditText withdrawAmt = findViewById(R.id.withdrawAmt);
                withdrawAmt.getText().clear();
                break;
            case R.id.cancelButton: //if cancel is clicked
            case R.id.cancelButton1:
            case R.id.cancelButton2:
                startActivity(main);
                break;
            case R.id.loginButton: //if login is clicked
            case R.id.continueButton:
                startActivity(trans);
                break;
            case R.id.cashDeposit: //if cash is clicked
                startActivity(cashDep);
                break;
            case R.id.checkDeposit: //if check is clicked
                startActivity(checkDep);
                break;
            case R.id.previous: //if previous dep choice is clicked
            case R.id.previous1:
                startActivity(depChoice);
                break;
            case R.id.enterButton:
                // TBD CASH DEPOSIT ENTERED
                break;
            case R.id.enterButton1:
                // TBD CHECK DEPOSIT ENTERED
                break;
            case R.id.withdrawBtn:
                startActivity(withdraw);
                break;
            case R.id.diffAmt:
                startActivity(withdrawDiff);
                break;
            case R.id.enterButton2:
                // TBD DIFF WITHDRAW AMOUNT ENTERED
                break;
            case R.id.transferBtn:
            case R.id.previous2:
                startActivity(transferType);
                break;
            case R.id.checkingAcct:
            case R.id.savingAcct:
                startActivity(outsideTransfer);
                break;
            case R.id.enterButton3:
                // TBD OUTSIDE TRANSFER ENTERED
                break;
            case R.id.depositBtn:
                startActivity(balance);
                break;
        }
    }
}