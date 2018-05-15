package com.example.mx.task1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MX on 2018/4/3.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>implements View.OnClickListener, View.OnLongClickListener{
    private List<String> names;
    private RecycleViewLisitenter.onItemClickLisitenter onItem;
    private RecycleViewLisitenter.onItemLongClickLisitenter onLongItem;
    public void setOnItemLongClickLisitenter(RecycleViewLisitenter.onItemLongClickLisitenter onLongItem){
        this.onLongItem = onLongItem;
    }
    public void setOnItemClickLisitenter(RecycleViewLisitenter.onItemClickLisitenter onItem){
        this.onItem = onItem;
    }
    /**
     * item点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(onItem!=null){
            onItem.onItemClick(v, (Integer) v.getTag());
        }
    }


    @Override
    public boolean onLongClick(View v) {
        if(onLongItem!=null){
            onLongItem.onItemLongClick(v, (Integer) v.getTag());
        }
        return true;
    }

     static class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView infoimg;
        TextView txlname;

        public ViewHolder(View itemView) {
            super(itemView);
            //infoimg=(ImageView) itemView.findViewById(R.id.univer_image);
            txlname=(TextView)itemView.findViewById(R.id.univer_name);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.univer_item,parent,false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name=names.get(position);
        holder.itemView.setTag(position);
        //holder.infoimg.setImageResource(info.getImgid());
        holder.txlname.setText(name);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }


    public InfoAdapter(List<String>name)
    {
        names=name;
    }


}
