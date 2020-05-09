package com.curiousapps.mvvm_practice.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.models.SchoolList;

public class SchoolListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView schoolName;
    TextView schoolAddress;
    TextView schoolCity;
    TextView schoolState;
    TextView schoolZip;
    TextView schoolPhone;
    ImageView schoolWebLink;

    private SchoolList schoolList;

    OnSchoolListListener onSchoolListListener;

    public SchoolListViewHolder(@NonNull View itemView, OnSchoolListListener onSchoolListListener) {
        super(itemView);
        this.onSchoolListListener = onSchoolListListener;

        Context context = itemView.getContext();
        schoolName = itemView.findViewById(R.id.text_view_school_name);
        schoolAddress = itemView.findViewById(R.id.text_view_school_address);
        schoolCity = itemView.findViewById(R.id.text_view_school_city);
        schoolState = itemView.findViewById(R.id.text_view_school_state);
        schoolZip = itemView.findViewById(R.id.text_view_school_zip);
        schoolPhone = itemView.findViewById(R.id.text_view_school_phone);
        schoolWebLink = itemView.findViewById(R.id.icon_web_link);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onSchoolListListener.onSchoolClick(getAdapterPosition());
    }
}
