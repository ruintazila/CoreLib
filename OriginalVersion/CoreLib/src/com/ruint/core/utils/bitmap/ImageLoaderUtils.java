/*
 * ImageLoaderUtil.java
 * classes : com.ruint.core.utils.bitmap.ImageLoaderUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午5:33:37
 */
package com.ruint.core.utils.bitmap;

import java.io.File;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ruint.corelib.R;
import com.ruint.lib.imageloader.core.DisplayImageOptions;
import com.ruint.lib.imageloader.core.ImageLoader;
import com.ruint.lib.imageloader.core.assist.FailReason;
import com.ruint.lib.imageloader.core.listener.ImageLoadingListener;

/**
 * com.ruint.core.utils.bitmap.ImageLoaderUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午5:33:37
 */
public class ImageLoaderUtils {
  private static ImageLoader imageLoader = ImageLoader.getInstance();

  private static DisplayImageOptions options;

  private static ImageLoaderUtils imageLoaderUtil = new ImageLoaderUtils();

  public static ImageLoaderUtils getInstance() {
    return imageLoaderUtil;
  }

  public void displayImage(String path, ImageView imageView) {
    if (!TextUtils.isEmpty(path)) {
      File picFile = new File(path);
      if (picFile.exists()) {
        path = "file://" + path;
      }
    }
    imageLoader.displayImage(path, imageView, getDisplayImageOptions(), null);
    return;
  }

  public void displayImage(String path, ImageView imageView, int defaultrDawableId) {
    if (!TextUtils.isEmpty(path)) {
      File picFile = new File(path);
      if (picFile.exists()) {
        path = "file://" + path;
      }
    }
    imageLoader.displayImage(path, imageView, getDisplayImageOptions(defaultrDawableId), null);
    return;
  }

  public void displayImage(String path, ImageView imageView, ImageLoadingListener listener) {
    if (!TextUtils.isEmpty(path)) {
      File picFile = new File(path);
      if (picFile.exists()) {
        path = "file://" + path;
      }
    }
    imageLoader.displayImage(path, imageView, getDisplayImageOptions(), listener);
    return;
  }

  public void displayImage(String path, ImageView image, DisplayImageOptions options) {
    if (!TextUtils.isEmpty(path)) {
      File picFile = new File(path);
      if (picFile.exists()) {
        path = "file://" + path;
      }
    }
    imageLoader.displayImage(path, image, options, null);
  }


  public void loadImage(String path) {
    File picFile = new File(path);
    if (picFile.exists()) {
      path = "file://" + path;
    }
    imageLoader.loadImage(path, getDisplayImageOptions(false, false), new ImageLoadingListener() {

      @Override
      public void onLoadingStarted(String imageUri, View view) {

      }

      @Override
      public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

      }

      @Override
      public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
      }

      @Override
      public void onLoadingCancelled(String imageUri, View view) {

      }
    });
  }

  public Bitmap loadBitmap(String path) {
    return imageLoader.loadImageSync(path);
  }

  public void loadImage(String path, final ImageLoaderCompleteListener imageLoaderCompleteListener) {
    if (path == null) {
      path = "";
    }
    File picFile = new File(path);
    if (picFile.exists()) {
      path = "file://" + path;
    }
    imageLoader.loadImage(path, getDisplayImageOptions(), new ImageLoadingListener() {

      @Override
      public void onLoadingStarted(String imageUri, View view) {

      }

      @Override
      public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

      }

      @Override
      public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (imageLoaderCompleteListener != null) {
          imageLoaderCompleteListener.onCompleteImageLoader(loadedImage);
        }
      }

      @Override
      public void onLoadingCancelled(String imageUri, View view) {

      }
    });
  }

  public void loadImage(String path, final ImageLoadingListener listener) {
    File picFile = new File(path);
    if (picFile.exists()) {
      path = "file://" + path;
    }
    imageLoader.loadImage(path, listener);
  }

  public synchronized static DisplayImageOptions getDisplayImageOptions(boolean cacheInMemeory, boolean cacheOnDisc) {
    options = new DisplayImageOptions.Builder().cacheInMemory(cacheInMemeory).cacheOnDisk(cacheOnDisc)
        .bitmapConfig(Bitmap.Config.RGB_565).showImageForEmptyUri(R.drawable.img_default)
        .showImageOnFail(R.drawable.img_default).build();
    return options;
  }

  public synchronized static DisplayImageOptions getDisplayImageOptions() {
    if (null == options) {
      options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
          .bitmapConfig(Bitmap.Config.RGB_565).showImageForEmptyUri(R.drawable.img_default)
          .showImageOnLoading(R.drawable.img_default).showImageOnFail(R.drawable.img_default).build();
    }
    return options;
  }

  public synchronized static DisplayImageOptions getDisplayImageOptions(int defaultDrawable) {
    return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
        .showImageOnLoading(defaultDrawable).showImageForEmptyUri(defaultDrawable).showImageOnFail(defaultDrawable)
        .build();
  }

  // public synchronized static DisplayImageOptions getDisplayImageOptions(int
  // imageOnLoading) {
  // if (null == options) {
  // options = new
  // DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
  // .bitmapConfig(Bitmap.Config.RGB_565).build();
  // }
  // return options;
  // }

  public interface ImageLoaderCompleteListener {
    public void onCompleteImageLoader(Bitmap bitmap);
  }
}
