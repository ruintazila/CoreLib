/*
 * BaseListAdapter.java
 * classes : com.ruint.core.adapter.BaseListAdapter
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:42:41
 */
package com.ruint.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * com.ruint.core.adapter.BaseListAdapter
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:42:41
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

  protected Context context;
  protected final List<T> mList;

  public BaseListAdapter(Context context) {
    this.context = context;
    this.mList = new ArrayList<T>();
  }

  public boolean injectList(List<? extends T> list) {
    return this.mList.addAll(list);
  }

  public void clear() {
    this.mList.clear();
  }

  @Override
  public int getCount() {
    return mList == null ? 0 : mList.size();
  }

  @Override
  public T getItem(int position) {
    return this.mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public boolean isEmpty() {
    return mList == null ? true : mList.isEmpty();
  }

  public T remove(int position) {
    return mList.remove(position);
  }

  public void addItem(T position) {
    this.mList.add(position);
  }

  public List<T> getList() {
    return mList;
  }
}
