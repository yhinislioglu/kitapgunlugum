package com.expoyazilim.kitapligim.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YAZILIM on 06.11.2017.
 */

public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.MyViewHolder>{
    List<BookShelf> mData = Collections.emptyList();
    LayoutInflater layoutInflater;
    private Context context;

    public BookShelfAdapter(Context context,List<BookShelf> data){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("BookShelf", "onCreateViewHolder Çağırıldı..");
        View v = layoutInflater.inflate(R.layout.list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("BookShelf", "onBindViewHolder Çağırıldı..");
        BookShelf current = mData.get(position);
        holder.mBookShelfName.setText(current.getBookShelf());
        Glide.with(context).load(R.drawable.iconbookshelf);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mBookShelfName, mBookNumber;
        ImageView mBookShelfImage,mDeleteImage,mShareImage;

        public MyViewHolder(View itemView){
            super(itemView);

            mBookShelfName = itemView.findViewById(R.id.bookShelfName);
            mBookShelfImage = itemView.findViewById(R.id.bookShelfImage);
            mDeleteImage = itemView.findViewById(R.id.deleteImage);
            mShareImage = itemView.findViewById(R.id.shareImage);
            mBookNumber = itemView.findViewById(R.id.bookNumber);
        }


    }
}
