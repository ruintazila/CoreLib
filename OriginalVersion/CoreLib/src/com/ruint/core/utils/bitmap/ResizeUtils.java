/*
 * ResizeUtil.java
 * classes : com.ruint.core.utils.bitmap.ResizeUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:43:18
 */
package com.ruint.core.utils.bitmap;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;

/**
 * com.ruint.core.utils.bitmap.ResizeUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:43:18
 */
public class ResizeUtils {

  public static Bitmap revitionImageSize(String path) {
    try {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(in, null, options);
      in.close();
      int i = 0;
      Bitmap bitmap = null;
      while (true) {
        if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
          in = new BufferedInputStream(new FileInputStream(new File(path)));
          options.inSampleSize = (int) Math.pow(2.0D, i);
          options.inJustDecodeBounds = false;
          bitmap = BitmapFactory.decodeStream(in, null, options);
          break;
        }
        i += 1;
      }
      return bitmap;
    } catch (Exception e) {
    }
    return null;
  }

  /**
   * 读取file图片并压缩成固定大小
   * 
   * @param path
   * @return
   */
  public static Bitmap getDecodeSampledBitmap(String path) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(path, options);
    options.inSampleSize = calculateInSampleSize(options, 100, 100);
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(path, options);
  }

  /**
   * 计算压缩比例方法
   * 
   * @param options
   * @param reqWidth
   * @param reqHeight
   * @return
   */
  public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {
      if (width > height) {
        inSampleSize = Math.round((float) height / (float) reqHeight);
      } else {
        inSampleSize = Math.round((float) width / (float) reqWidth);
      }
    }
    return inSampleSize;
  }

  /**
   * drawable转bitmap
   * 
   * @param drawable
   * @return
   */
  public static Bitmap drawableToBitmap(Drawable drawable)// drawable 转换成bitmap
  {
    int width = drawable.getIntrinsicWidth(); // 取drawable的长宽
    int height = drawable.getIntrinsicHeight();
    Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
        : Bitmap.Config.RGB_565; // 取drawable的颜色格式
    Bitmap bitmap = Bitmap.createBitmap(width, height, config); // 建立对应bitmap
    Canvas canvas = new Canvas(bitmap); // 建立对应bitmap的画布
    drawable.setBounds(0, 0, width, height);
    drawable.draw(canvas); // 把drawable内容画到画布中
    return bitmap;
  }

  /**
   * bitmap缩放后转换成drawable
   * 
   * @param drawable
   * @param w
   * @param h
   * @return
   */
  @SuppressWarnings("deprecation")
  public static Drawable zoomDrawable(Drawable drawable, Display display, float density) {
    int width = drawable.getIntrinsicWidth();
    int height = drawable.getIntrinsicHeight();
    int reqWidth = (int) (display.getWidth()) - 30;
    Bitmap oldbmp = drawableToBitmap(drawable);// drawable转换成bitmap
    Matrix matrix = new Matrix(); // 创建操作图片用的Matrix对象
    float inSampleSizeW = 1f;
    float inSampleSizeH = 1f;
    if (width > reqWidth) {
      inSampleSizeW = (float) reqWidth / (float) width;
      inSampleSizeH = ((float) reqWidth / (float) height);
    } else {
      return drawable;
    }
    matrix.postScale(inSampleSizeW, inSampleSizeH); // 设置缩放比例
    Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true); // 建立新的bitmap，其内容是对原bitmap的缩放后的图
    return new BitmapDrawable(newbmp); // 把bitmap转换成drawable并返回
  }

  public static byte[] Bitmap2Bytes(Bitmap bitmap) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
    return baos.toByteArray();
  }

  public static byte[] revitionBitmap(String path) throws IOException {
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(in, null, options);
    in.close();
    int i = 0;
    Bitmap bitmap = null;
    while (true) {
      if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
        in = new BufferedInputStream(new FileInputStream(new File(path)));
        options.inSampleSize = (int) Math.pow(2.0D, i);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeStream(in, null, options);
        break;
      }
      i += 1;
    }
    byte[] data = Bitmap2Bytes(bitmap);
    bitmap.recycle();
    bitmap = null;
    return data;
  }

  public static byte[] revitionBitmap(File file) throws IOException {
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(in, null, options);
    in.close();
    int i = 0;
    Bitmap bitmap = null;
    while (true) {
      if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
        in = new BufferedInputStream(new FileInputStream(file));
        options.inSampleSize = (int) Math.pow(2.0D, i);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeStream(in, null, options);
        break;
      }
      i += 1;
    }
    byte[] data = Bitmap2Bytes(bitmap);
    bitmap.recycle();
    bitmap = null;
    return data;
  }
}
