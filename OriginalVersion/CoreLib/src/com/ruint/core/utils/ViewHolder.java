/*
 * ViewHolder.java
 * classes : com.house365.cdecoration.ui.util.ViewHolder
 * @author ruint
 * V 1.0.0
 * Create at 2014-8-6 下午12:40:00
 */
package com.ruint.core.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * com.house365.cdecoration.ui.util.ViewHolder
 * @author ruint
 * create at 2014-8-6 下午12:40:00
 */
public class ViewHolder {

  @SuppressWarnings("unchecked")
  public static <T extends View> T get(View view, int id) {
    SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
    if (viewHolder == null) {
      viewHolder = new SparseArray<View>();
      view.setTag(viewHolder);
    }
    View childView = viewHolder.get(id);
    if (childView == null) {
      childView = view.findViewById(id);
      viewHolder.put(id, childView);
    }
    return (T) childView;
  }
}

