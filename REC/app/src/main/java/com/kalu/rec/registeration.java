package com.kalu.rec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class registeration extends AppCompatActivity {
    EditText fullname,email,username,password,phoneno;
    Button register;
    RadioGroup sexchoice;
    String sex;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        fullname=findViewById(R.id.editText2name);
        username=findViewById(R.id.editText3username);
        email=findViewById(R.id.editText6email);
        password=findViewById(R.id.editText5password);
        phoneno=findViewById(R.id.editText8phone);
        sexchoice=findViewById(R.id.radioGroup);
        progressBar=findViewById(R.id.progressBar3);
        int selectedId = sexchoice.getCheckedRadioButtonId();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        // find the radiobutton by returned id
        sexchoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button=findViewById(checkedId);
                sex=button.getText().toString();
            }
        });
        register=findViewById(R.id.buttonregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String name=fullname.getText().toString();
               final String uname=username.getText().toString();
                final String emails=email.getText().toString();
                final String passwo=password.getText().toString();
                final String phonen=phoneno.getText().toString();
                if(TextUtils.isEmpty(name)){
                    email.setError("Error nigga");
                }
                if(TextUtils.isEmpty(uname)){
                    password.setError("Error nigga");
                    return;
                }
                if(TextUtils.isEmpty(emails)){
                    email.setError("Error nigga");
                    return;
                }
                if(TextUtils.isEmpty(passwo)){
                    password.setError("Error nigga");
                    return;
                }
                Toast.makeText(getApplicationContext(), "Loading please wait ", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(emails,passwo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.INVISIBLE);
                            String accid=firebaseAuth.getCurrentUser().getUid();
                            AccountModel accountModel=new AccountModel(emails,name,phonen,sex,uname);
                            DocumentReference accdoc=firebaseFirestore.collection("Account").document(accid);
                            accdoc.set(accountModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                               Intent torecycler=new Intent(registeration.this,MainActivity.class);
                               startActivity(torecycler);
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Data Is not added",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }
}
