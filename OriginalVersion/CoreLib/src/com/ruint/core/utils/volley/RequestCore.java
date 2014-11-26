/*
 * RequestManager.java
 * classes : ruint.core.utils.volley.RequestManager
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午11:19:39
 */
package com.ruint.core.utils.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * ruint.core.utils.volley.RequestManager
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:19:39
 */
public class RequestCore {

  private static RequestQueue mRequestQueue;

  private RequestCore() {
    // no instances
  }

  /**
   * @param context
   *          application context
   */
  public static void init(Context context) {
    mRequestQueue = Volley.newRequestQueue(context, new MultiPartStack());
  }

  /**
   * @return instance of the queue
   * @throws IllegalStatException
   *           if init has not yet been called
   */
  public static RequestQueue getRequestQueue() {
    if (mRequestQueue != null) {
      return mRequestQueue;
    } else {
      throw new IllegalStateException("Not initialized");
    }
  }
}
