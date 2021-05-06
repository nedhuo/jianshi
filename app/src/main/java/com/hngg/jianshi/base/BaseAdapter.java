package com.hngg.jianshi.base;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{
    protected final String TAG = this.getClass().getSimpleName();
}
