package com.kalu.rec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccountModel acc;
    private FirestoreRecyclerAdapter<AccountModel,AccountHolder> adapter;
    String userId;
    FirebaseAuth firebaseAuth;
    Button logout;
     FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
         db= FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        Query query=db.collection("Account").orderBy("fullname", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<AccountModel> options=new FirestoreRecyclerOptions.Builder<AccountModel>()
                .setQuery(query,AccountModel.class).build();
        adapter= new FirestoreRecyclerAdapter<AccountModel, AccountHolder>(options) {
            @NonNull
            @Override
            public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);


                return new AccountHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final AccountHolder accountHolder, final int i, @NonNull final AccountModel accountModel) {
                accountHolder.mtitle.setText(accountModel.getFullname());
                accountHolder.mdescri.setText(accountModel.getUsername());
                accountHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent todetail=new Intent(MainActivity.this,DetailAccunts.class);
                        todetail.putExtra("name",accountModel.getFullname().toString());
                        todetail.putExtra("ename",accountModel.getEmail().toString());
                        todetail.putExtra("pname",accountModel.getPhoneno().toString());
                        todetail.putExtra("sex",accountModel.getSex().toString());
                        todetail.putExtra("uname",accountModel.getUsername().toString());
                        startActivity(todetail);
                    }
                });
                accountHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                       // DocumentReference delete=db.collection("Account").do
                        final String userId=adapter.getSnapshots().getSnapshot(i).getId();
                        if(userId.equals(firebaseAuth.getCurrentUser().getUid())){
                            Toast.makeText(getApplicationContext(), "How can u delete YOURSELF???", Toast.LENGTH_SHORT).show();
                    }else  {
                            DocumentReference documentReference=db.collection("Account").document(userId);
                            documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(getApplicationContext(), "Deleted!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }



                        return false;
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
        logout=findViewById(R.id.buttonlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            logout();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null){
            adapter.stopListening();
        }
    }
    private void logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(MainActivity.this,loginpage.class));
        finish();
    }

}
