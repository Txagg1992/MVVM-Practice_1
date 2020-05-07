package com.curiousapps.mvvm_practice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.activities.WebViewActivity;
import com.curiousapps.mvvm_practice.models.SchoolList;

import java.util.List;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;

    private List<SchoolList> mSchoolList;
    private OnSchoolListListener mOnSchoolListListener;

    private SchoolRecyclerViewAdapter(Context context){
        this.context = context;
    }

    public SchoolRecyclerViewAdapter(OnSchoolListListener mOnSchoolListListener) {
        this.mOnSchoolListListener = mOnSchoolListListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_item, parent, false);
        return new SchoolListViewHolder(view, mOnSchoolListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((SchoolListViewHolder)holder).schoolName.setText(mSchoolList.get(position).getSchool_name());
        ((SchoolListViewHolder)holder).schoolAddress.setText(mSchoolList.get(position).getPrimary_address_line_1());
        ((SchoolListViewHolder)holder).schoolCity.setText(mSchoolList.get(position).getCity());
        ((SchoolListViewHolder)holder).schoolState.setText(mSchoolList.get(position).getState_code());
        ((SchoolListViewHolder)holder).schoolZip.setText(mSchoolList.get(position).getZip());
        ((SchoolListViewHolder)holder).schoolPhone.setText(mSchoolList.get(position).getPhone_number());
        ((SchoolListViewHolder)holder).schoolWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add intent to open web page
                Intent intent = new Intent(context, WebViewActivity.class);
                //putExtra
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mSchoolList != null){
            return mSchoolList.size();
        }
        return 0;
    }

    public void setSchoolList(List<SchoolList> schoolList){
        mSchoolList = schoolList;
        notifyDataSetChanged();
    }
}
