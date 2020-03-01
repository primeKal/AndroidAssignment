package com.kalu.rec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailAccunts extends AppCompatActivity {



            TextView name,username,sex,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_accunts);
        name=findViewById(R.id.textView2fullname);
        email=findViewById(R.id.textView5email);
        phone=findViewById(R.id.textView6phone);
        sex=findViewById(R.id.textView8sex);
        username=findViewById(R.id.textView4username);

        Bundle extraAccount=getIntent().getExtras();
        name.setText(extraAccount.getString("name"));
        email.setText(extraAccount.getString("ename"));
        username.setText(extraAccount.getString("uname"));
        phone.setText(extraAccount.getString("pname"));
        sex.setText(extraAccount.getString("sex"));

    }
}
