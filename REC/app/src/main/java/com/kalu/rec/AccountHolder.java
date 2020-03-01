package com.kalu.rec;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalu.rec.R;

public class AccountHolder extends RecyclerView.ViewHolder {

    TextView mtitle,mdescri;


    public AccountHolder (@NonNull View itemView){
        super(itemView);
        this.mtitle=itemView.findViewById(R.id.imageIv);
        this.mdescri=itemView.findViewById(R.id.TitleTv);
}
}
