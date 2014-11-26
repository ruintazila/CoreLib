package com.ruint.lib.bitmaploader.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * com.ruint.lib.bitmapLoader.callback
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:10:50
 */
public interface BitmapSetter<T extends View> {
  void setBitmap(T container, Bitmap bitmap);

  void setDrawable(T container, Drawable drawable);

  Drawable getDrawable(T container);
}
