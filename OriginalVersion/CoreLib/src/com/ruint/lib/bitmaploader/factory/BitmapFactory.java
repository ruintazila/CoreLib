package com.ruint.lib.bitmaploader.factory;

import android.graphics.Bitmap;

/**
 * com.ruint.lib.bitmapLoader.factory
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:10:50
 */
public interface BitmapFactory {

  BitmapFactory cloneNew();

  Bitmap createBitmap(Bitmap rawBitmap);
}
