package com.kalu.rec;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.core.Query;

public class loginpage extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login,create;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        email=findViewById(R.id.editText2email);
        password=findViewById(R.id.editText4password);
        login=findViewById(R.id.buttonlogin);
        create=findViewById(R.id.button2create);
        progressBar=findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent toyurpage=new Intent(loginpage.this,MainActivity.class);
            startActivity(toyurpage);
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailS=email.getText().toString();
                String passwordS=password.getText().toString();

                if(TextUtils.isEmpty(emailS)){
                    email.setError("Error nigga");
                    return;
                }
                if(TextUtils.isEmpty(passwordS)){
                    password.setError("Error nigga");
                    return;
                }
                //Authenticate from Firebase
                Toast.makeText(getApplicationContext(),"Loading please wait ",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
             firebaseAuth.signInWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         progressBar.setVisibility(View.INVISIBLE);
                         Intent tomainactivtiy=new Intent(loginpage.this,MainActivity.class);
                         startActivity(tomainactivtiy);
                     }else {
                         Toast.makeText(getApplicationContext(), "Unable to Connect,Card geza man", Toast.LENGTH_SHORT).show();
                     return;
                     }
                 }
             });



            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toregisterationActivity=new Intent(loginpage.this,registeration.class);

                startActivity(toregisterationActivity);



            }
});
    } }


