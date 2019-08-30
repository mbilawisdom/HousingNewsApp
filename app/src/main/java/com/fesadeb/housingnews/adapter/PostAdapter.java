package com.fesadeb.housingnews.adapter;//package com.fesadeb.housingnews.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.example.housingnews.R;
//import com.fesadeb.housingnews.activities.PostActivity;
//import com.fesadeb.housingnews.model.PostList;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
//
//    private Context context;
//    private ArrayList<PostList> list;
//
//    public PostAdapter(Context context, ArrayList<PostList> list) {
//        this.context = context;
//        this.list = list;
//
//    }
//
//
//    @Override
//    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.post_list, parent, false);
//        return new PostViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(final PostAdapter.PostViewHolder holder, int position) {
//
//
//        final PostList item = list.get(position);
//
//
//        Document document = Jsoup.parse(item.subtitle);
//        holder.postDescription.setText(document.text());
//        holder.postTitle.setText(item.title);
//
//
////        Glide
////                .with(this.context)
////                .load(item.getImg())
////                .asBitmap()
////                .into(new SimpleTarget<Bitmap>(200,200) {
////                    @Override
////                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
////                        holder.postImage.setImageBitmap(resource); // Possibly runOnUiThread()
////                    }
////                });
//
////        Elements elements = document.select("img");
////        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);
//
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(context, PostActivity.class);
////                intent.putExtra("url", item.getUrl());
////                context.startActivity(intent);
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class PostViewHolder extends RecyclerView.ViewHolder {
//        ImageView postImage;
//        TextView postTitle;
//        TextView postDescription;
//
//        public PostViewHolder(View itemView) {
//            super(itemView);
//            postImage = (ImageView) itemView.findViewById(R.id.img_post);
//            postTitle = (TextView) itemView.findViewById(R.id.title_post);
//            postDescription = (TextView) itemView.findViewById(R.id.desc_post);
//        }
//    }
//
//}

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fesadeb.housingnews.R;
import com.fesadeb.housingnews.activities.PostActivity;
import com.fesadeb.housingnews.model.PostList;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter {

    private ArrayList<PostList> dataset;
    private Context mContext;

    public PostAdapter(ArrayList<PostList> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{


        TextView title, subtitle;
        ImageView imageView;
        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView)  itemView.findViewById(R.id.title_post);
            this.subtitle = (TextView) itemView.findViewById(R.id.desc_post);
            this.imageView = (ImageView) itemView.findViewById(R.id.img_post);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new ImageTypeViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final  int position) {
        final PostList object = dataset.get(position);

        ( (ImageTypeViewHolder) holder).title.setText( object.title );
        ( (ImageTypeViewHolder) holder).subtitle.setText( object.subtitle );

        Glide.with(mContext).load(object.Image).into(((ImageTypeViewHolder) holder).imageView);
        ( (ImageTypeViewHolder) holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });
        ( (ImageTypeViewHolder) holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });
        ( (ImageTypeViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });

        ( (ImageTypeViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });

        /// dataset.get(position)
    }

    @Override
    public int getItemCount() {
        return dataset.size() ;
    }
}
