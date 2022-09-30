package com.example.tog_s;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.PaymentApp;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;


public class Donate extends AppCompatActivity implements PaymentStatusListener {
    String amnt , msgg , token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Button donate = (Button) findViewById(R.id.loginbtn3);
        EditText amount1 = (EditText) findViewById(R.id.textInputEditTextAmount);
        EditText message = (EditText) findViewById(R.id.textInputEditTextMessage);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        token = sh.getString("token", "");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

        donate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String amount = amount1.getText().toString();
                amnt = amount;
                String upi = "9548425684@upi";
                String desc = message.getText().toString();
                msgg = desc;
                if (TextUtils.isEmpty(amount) && TextUtils.isEmpty(desc)) {
                    Toast.makeText(getApplicationContext(), "Please enter all the details..", Toast.LENGTH_SHORT).show();
                } else {
                    makePayment(amount, upi, desc, transcId);
                }
            }

//            @Override
//            public void onClick(View view) {
//                int amt = Integer.parseInt(amount.getText().toString());
//                String msg = message.getText().toString();
//
//
//                Auth auth = new Auth();
//                Intent intent = new Intent(getApplicationContext(),menu.class);
//                onBackPressed();
//                auth.donateFunds(token,findViewById(R.id.donatefund),amt,msg,getApplicationContext(),intent);
//            }
        });
    }

    private void makePayment(String amount, String upi, String desc, String transactionId) {
        try{
            EasyUpiPayment builder = new EasyUpiPayment.Builder(this)
                    .setPayeeVpa(upi)
                    .setPayeeName("Shubham")
                    .setPayeeMerchantCode("12345")
                    .setTransactionId(transactionId)
                    .setTransactionRefId(transactionId)
                    .setDescription(desc)
                    .setAmount(amount)
                    .build();
//            Toast.makeText(this, "Heyyyyy", Toast.LENGTH_SHORT).show();
//            builder.setPaymentStatusListener(this);
            builder.startPayment();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCompleted(@NonNull TransactionDetails transactionDetails) {
        String transacid = transactionDetails.getTransactionId();
        String transcDetails = transactionDetails.getTransactionId() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show();
        Auth auth = new Auth();
        auth.donateFunds(token,findViewById(R.id.donatefund),amnt,msgg,getApplicationContext(),transacid);
        onBackPressed();
    }



}