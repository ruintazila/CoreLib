/*
 * BaseHttpApi.java
 * classes : com.ruint.core.utils.volley.BaseHttpApi
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-25 下午5:42:26
 */
package com.ruint.core.utils.volley;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.android.volley.Response.Listener;
import com.ruint.core.utils.URLBuilder;

/**
 * com.ruint.core.utils.volley.BaseHttpApi
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 下午5:42:26
 */
public abstract class BaseHttpApi extends URLBuilder {

  private static String header;

  /**
   * Subclass may override this method
   * 
   * @return
   */
  public String getHeader() {
    return BaseHttpApi.header;
  }

  public void setHeader(String header) {
    BaseHttpApi.header = header;
  }

  private static RequestManager mRequestManager = RequestManager.getInstance();

  protected abstract <T> void request(T t, String action, String... params);

  /**
   * GET request
   * 
   * @param t
   *          T extents BaseBean
   * @param action
   *          request method name
   * @param pList
   *          params list
   * @param listener
   */
  protected <T> void get(T t, String action, List<NameValuePair> pList, Listener<T> listener) {
    addCommonParams(pList);
    mRequestManager.get(t, makeGetRequest(getHeader(), action, pList), listener);
  }

  protected <T> void post(T t, String action, Map<String, String> params, Map<String, File> files, boolean compress,
      Listener<T> listener) {
    addCommonParams(params);
    mRequestManager.post(t, makeRequestHeader(getHeader(), action), params, files, compress, listener);
  }

  protected <T> void post(T t, String action, List<NameValuePair> pList, Map<String, File> files, boolean compress,
      Listener<T> listener) {
    addCommonParams(pList);
    mRequestManager.post(t, makeRequestHeader(getHeader(), action), makeMap(pList), files, compress, listener);
  }

  protected <T> void post(T t, String action, List<NameValuePair> pList, Listener<T> listener) {
    addCommonParams(pList);
    mRequestManager.post(t, makeRequestHeader(getHeader(), action), makeMap(pList), null, false, listener);
  }

  protected <T> void post(T t, String action, Map<String, String> params, Listener<T> listener) {
    addCommonParams(params);
    mRequestManager.post(t, makeRequestHeader(getHeader(), action), params, null, false, listener);
  }

}
