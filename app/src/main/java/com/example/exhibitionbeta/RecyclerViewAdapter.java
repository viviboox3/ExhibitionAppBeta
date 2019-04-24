package com.example.exhibitionbeta;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;


import com.example.exhibitionbeta.models.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    //public static AppDatabase appDatabase;
    private MainViewModel mViewModel;
    private Context mContext;
    private ArrayList<String> mNames, mPhones;
    private ArrayList<Integer> mUids;
    private int uid, tempPos;
    private String phone;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> phones, ArrayList<Integer> uids) {
        this.mContext = context;
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "users").allowMainThreadQueries().build();
        this.mContext = context;
        this.mNames = names;
        this.mPhones = phones;
        this.mUids = uids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listusers, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        mViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(MainViewModel.class);
        holder.name.setText(mNames.get(position));
        holder.phone.setText(mPhones.get(position));
        holder.number.setText(position + "");


        TextView messageView = holder.message;
        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: actually send message (call post)
                phone = mPhones.get(position);
                Log.d(TAG, "onClick: clicked message for user " + uid);
                Intent messageIntent = new Intent(v.getContext(), MessageActivity.class);
                Log.d(TAG, "onClick: clicked sendMessage for user " + uid);
                messageIntent.putExtra("USER_PHONE", phone);
                v.getContext().startActivity(messageIntent);
            }
        });
        
        TextView memoView = holder.memo;
        memoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: actually save a memo (add field to model)
                uid = mUids.get(position);
                Log.d(TAG, "onClick: clicked memo for user " + uid);
                Intent textIntent = new Intent(v.getContext(), TextActivity.class);
                Log.d(TAG, "onClick: clicked saveMemo for user " + uid);
                textIntent.putExtra("USER_ID", uid);
                v.getContext().startActivity(textIntent);
            }
        });
        
        TextView deleteView = holder.delete;
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = mUids.get(position);
                tempPos = position;
                Log.d(TAG, "onClick: clicked delete for user " + uid);
                Log.d(TAG, "onClick: position " + tempPos);
                //appDatabase.userDAO().deleteUserWithId(uid);
                //Log.d(TAG, "onClick: There are total: " + appDatabase.userDAO().getAllUserCount() + " users");

                mNames.remove(tempPos);
                mPhones.remove(tempPos);
                mViewModel.deleteUser(new Integer(uid));
                notifyItemRemoved(tempPos);
                notifyItemRangeChanged(position, mNames.size());
                //notifyItemRangeChanged(position, mPhones.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        GridLayout parentLayout;
        TextView number, name, phone, message, memo, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            number = itemView.findViewById(R.id.number);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            message = itemView.findViewById(R.id.message);
            memo = itemView.findViewById(R.id.memo);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
