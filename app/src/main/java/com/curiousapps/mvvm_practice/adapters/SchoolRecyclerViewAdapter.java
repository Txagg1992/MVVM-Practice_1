package com.curiousapps.mvvm_practice.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.curiousapps.mvvm_practice.R;
import com.curiousapps.mvvm_practice.activities.WebViewActivity;
import com.curiousapps.mvvm_practice.models.SchoolList;
import com.curiousapps.mvvm_practice.util.Constants;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private static final int SCHOOL_LIST_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int EXHAUSTED_TYPE = 3;

    private List<SchoolList> mSchoolList;
    private OnSchoolListListener mOnSchoolListListener;

    private SchoolRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public SchoolRecyclerViewAdapter(OnSchoolListListener mOnSchoolListListener) {
        this.mOnSchoolListListener = mOnSchoolListListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case SCHOOL_LIST_TYPE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.school_list_item, parent, false);
                return new SchoolListViewHolder(view, mOnSchoolListListener);
            }
            case LOADING_TYPE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_loading_dots, parent, false);
                return new LoadingViewHolder(view);
            }
            case EXHAUSTED_TYPE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_search_exhausted, parent, false);
                return new SearchExhaustedViewHolder(view);
            }
            default: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.school_list_item, parent, false);
                return new SchoolListViewHolder(view, mOnSchoolListListener);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        int itemViewType = getItemViewType(position);
        if (itemViewType == SCHOOL_LIST_TYPE) {
            ((SchoolListViewHolder) holder).schoolName.setText(mSchoolList.get(position).getSchool_name());
            ((SchoolListViewHolder) holder).schoolAddress.setText(mSchoolList.get(position).getPrimary_address_line_1());
            ((SchoolListViewHolder) holder).schoolCity.setText(mSchoolList.get(position).getCity());
            ((SchoolListViewHolder) holder).schoolState.setText(mSchoolList.get(position).getState_code());
            ((SchoolListViewHolder) holder).schoolZip.setText(mSchoolList.get(position).getZip());
            ((SchoolListViewHolder) holder).schoolPhone.setText(mSchoolList.get(position).getPhone_number());

            ((SchoolListViewHolder) holder).schoolWebLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Link Button", "Clicked: " + mSchoolList.get(position).getWebsite().toString());

                    //Add intent to open web page
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("link", mSchoolList.get(position).getWebsite());
                    context.startActivity(intent);
                }
            });

            ((SchoolListViewHolder) holder).schoolPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Phone Button", "Clicked: " + mSchoolList.get(position).getPhone_number().toString());

                        String uri = "tel:" + mSchoolList.get(position).getPhone_number();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mSchoolList.get(position).getSchool_name().equals("Loading...")) {
            return LOADING_TYPE;
        } else if (mSchoolList.get(position).getSchool_name().equals("EXHAUSTED..")) {
            return EXHAUSTED_TYPE;
        }else if (position == mSchoolList.size() - 1
                && position != 0
                && !mSchoolList.get(position).getSchool_name().equals("EXHAUSTED..")) {
            return LOADING_TYPE;
        }else {
            return SCHOOL_LIST_TYPE;
        }
    }

    public void setQueryExhausted(){
        hideLoading();
        SchoolList exhaustedSchoolList = new SchoolList();
        exhaustedSchoolList.setSchool_name("EXHAUSTED...");
        mSchoolList.add(exhaustedSchoolList);
        notifyDataSetChanged();
    }

    public void hideLoading(){
        if (isLoading()){
            for (SchoolList schoolList: mSchoolList){
                if (schoolList.getSchool_name().equals("Loading...")){
                    mSchoolList.remove(schoolList);
                }
            }
            notifyDataSetChanged();
        }
    }

    public void displayLoading() {
        if (!isLoading()) {
            SchoolList schoolList = new SchoolList();
            schoolList.setSchool_name("Loading...");
            List<SchoolList> loadingList = new ArrayList<>();
            loadingList.add(schoolList);
            mSchoolList = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if (mSchoolList != null){
            if (mSchoolList.size() > 0){
                if (mSchoolList.get(mSchoolList.size()-1).equals("Loading...")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if (mSchoolList != null) {
            return mSchoolList.size();
        }
        return 0;
    }

    public void setSchoolList(List<SchoolList> schoolList) {
        mSchoolList = schoolList;
        notifyDataSetChanged();
    }

    public SchoolList getSelectedSchool(int position){
        if (mSchoolList != null){
            if (mSchoolList.size() > 0){
                return mSchoolList.get(position);
            }
        }
        return null;
    }
}
