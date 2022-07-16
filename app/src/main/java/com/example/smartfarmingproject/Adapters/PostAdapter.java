package com.example.smartfarmingproject.Adapters;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartfarmingproject.Activities.PostDetailsActivity;
import com.example.smartfarmingproject.Models.Post;
import com.example.smartfarmingproject.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context mContext;
    List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.raw_post_item , parent , false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgPostProfile);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView imgPost;
        ImageView imgPostProfile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.row_post_title);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailsActivity = new Intent(mContext, PostDetailsActivity.class);
                    int position = getAdapterPosition();

                    postDetailsActivity.putExtra("title", mData.get(position).getTitle());
                    postDetailsActivity.putExtra("postImage", mData.get(position).getPicture());
                    postDetailsActivity.putExtra("description", mData.get(position).getDescription());
                    postDetailsActivity.putExtra("postKey", mData.get(position).getPostKey());
                    postDetailsActivity.putExtra("userPhoto", mData.get(position).getUserPhoto());
                    //postDetailsActivity.putExtra("userName", mData.get(position).getUsername);
                    long timestamp = (long) mData.get(position).getTimeStamp();
                    postDetailsActivity.putExtra("postDate", timestamp);

                    mContext.startActivity(postDetailsActivity);

                }
            });

        }
    }
}
