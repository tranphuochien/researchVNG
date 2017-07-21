/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demoapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.root.demoapp.R;
import com.example.root.demoapp.data.model.Friend;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    public interface OnItemClickListener {
        void onUserItemClicked(Friend userModel);
    }

    private List<Friend> usersCollection;
    private final LayoutInflater layoutInflater;
    private final Context context;

    private OnItemClickListener onItemClickListener;

    @Inject
    FriendsAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usersCollection = Collections.emptyList();
        this.context = context;
    }

    @Override public int getItemCount() {
        return (this.usersCollection != null) ? this.usersCollection.size() : 0;
    }

    @Override public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
        return new FriendViewHolder(view);
    }

    @Override public void onBindViewHolder(FriendViewHolder holder, final int position) {
        final Friend friend = this.usersCollection.get(position);
        holder.textViewTitle.setText(friend.getName());
        Glide.with(context)
                .load(friend.getPicture().getData().getUrl())
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (FriendsAdapter.this.onItemClickListener != null) {
                    FriendsAdapter.this.onItemClickListener.onUserItemClicked(friend);
                }
            }
        });
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setUsersCollection(Collection<Friend> usersCollection) {
        this.validateUsersCollection(usersCollection);

        this.usersCollection = (List<Friend>) usersCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<Friend> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list friend is null");
        }
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title) TextView textViewTitle;
        @Bind(R.id.avatar) ImageView imgAvatar;

        FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
