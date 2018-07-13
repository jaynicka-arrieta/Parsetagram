package ilovecats.com.parsetagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.parceler.Parcels;

import java.util.List;
import java.util.Locale;

import java.util.List;

import ilovecats.com.parsetagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private static List<Post> mPosts;
    static Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get data according to position
        Post post = mPosts.get(position);

        //populate the views according to data
        holder.tvHandle.setText(post.getUser().getUsername().toString());
        holder.tvHandle.setTextColor(Color.BLACK);

        holder.tvDescription.setTypeface(null, Typeface.BOLD);
        holder.tvDescription.setText(post.getUser().getUsername().toString() + " - ");
        holder.tvDescription.setTypeface(null, Typeface.NORMAL);
        holder.tvDescription.append(post.getDescription().toString());
        holder.tvDescription.setTextColor(Color.BLACK);

        String timestamp = post.getCreatedAt().toString().substring(4, 11);
        holder.tvTimeStamp.setText(timestamp);



        GlideApp.with(context)
                .load(post.getImage().getUrl())
                .into(holder.ivImagePost);
    }

    //create ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImagePost;
        public TextView tvDescription;
        public TextView tvHandle;
        public TextView tvTimeStamp;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImagePost = (ImageView) itemView.findViewById(R.id.ivImagePost);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvHandle = (TextView) itemView.findViewById(R.id.tvHandle);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);

            ivImagePost.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = mPosts.get(position);
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("post", Parcels.wrap(post));
                context.startActivity(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }
}
