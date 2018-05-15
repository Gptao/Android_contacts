package com.example.mx.task1;

/**
 * Created by MX on 2018/4/3.
 */

import android.view.View;



public class RecycleViewLisitenter {

    /**
     * RecycleView的条目点击监听
     */
    public interface onItemClickLisitenter{
        void onItemClick(View v, int position);
    };
    /**
     * RecycleView的条目长按点击监听
     */
    public interface onItemLongClickLisitenter{
        void onItemLongClick(View v, int position);
    };
}