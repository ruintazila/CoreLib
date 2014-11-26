/*
 * VolleyUtils.java
 * classes : ruint.core.utils.volley.VolleyUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午11:18:38
 */
package com.ruint.core.utils.volley;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.entity.mime.content.ByteArrayBody;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.ruint.core.utils.bitmap.ResizeUtils;
import com.ruint.core.utils.preferences.CorePreferences;
import com.ruint.core.utils.ui.ToastUtils;
import com.ruint.core.view.supertoasts.SuperToast.Animations;
import com.ruint.core.view.supertoasts.SuperToast.Type;

/**
 * ruint.core.utils.volley.VolleyUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:18:38
 */
public class RequestManager {

  private Context context;

  private static RequestManager mInstance;
  private RequestQueue mRequestQueue;

  private ErrorListener errorListener = new ErrorListener() {

    @Override
    public void onErrorResponse(VolleyError error) {
      if (error != null) {
        if (error.networkResponse != null)
          CorePreferences.DEBUG("VolleyError  : " + new String(error.networkResponse.data));
      }
      ToastUtils.showCardToast((Activity) context, Type.STANDARD, "数据请求失败", ToastUtils.SWIPE_TO_DISMISS,
          Animations.POPUP, null);
    }
  };

  public static RequestManager getInstance() {
    if (mInstance == null) {
      mInstance = new RequestManager();
      mInstance.init();
    }
    return mInstance;
  }

  private void init() {
    RequestCore.init(context);
    mRequestQueue = RequestCore.getRequestQueue();
  }

  public <T> void get(T t, String url, Listener<T> responseListener) {
    GsonRequest<T> request = new GsonRequest<T>(Method.GET, url, t.getClass(), responseListener, errorListener);
    mRequestQueue.add(request);
  }

  public <T> void post(T t, String url, final Map<String, String> params, final Map<String, File> files,
      final boolean isCompress, Listener<T> responseListener) {
    MultiPartStringRequest<T> request = new MultiPartStringRequest<T>(url, t.getClass(), responseListener,
        errorListener) {

      @Override
      public Map<String, File> getFile() {
        if (!isCompress) {
          return files;
        }
        return super.getFile();
      }

      @Override
      public Map<String, String> getString() {
        return params;
      }

      @Override
      public Map<String, ByteArrayBody> getByte() {
        if (isCompress) {
          Iterator<Entry<String, File>> iterator = files.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<String, File> entry = iterator.next();
            String key = entry.getKey();
            File file = entry.getValue();
            CorePreferences.DEBUG(key + "\n" + file.getAbsolutePath() + "\n" + file.length());
            try {
              super.getByte().put(key, new ByteArrayBody(ResizeUtils.revitionBitmap(file), key));
            } catch (IOException e) {
              CorePreferences.DEBUG(key + " : " + "revition failed" + e.getMessage());
            }
          }
        }
        return super.getByte();
      }
    };
    mRequestQueue.add(request);
  }

  public void cancelAll(RequestFilter filter) {
    mRequestQueue.cancelAll(filter);
  }

  public void cancelAll(final Object tag) {
    mRequestQueue.cancelAll(tag);
  }
}
