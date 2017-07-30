package com.example.roman.githubapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roman.githubapp.BaseApplication;
import com.example.roman.githubapp.R;
import com.example.roman.githubapp.server_data.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roman on 30/07/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> userList;
    private BaseApplication app;

    public UsersAdapter(BaseApplication app, List<User> userList) {
        this.app = app;
        this.userList = userList;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(app).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserName.setText(user.getUserName());
        Picasso.with(app).load(user.getUserImage()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvUserName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView)itemView.findViewById(R.id.ivImage);
            tvUserName = (TextView)itemView.findViewById(R.id.tvUserName);
        }
    }
}
