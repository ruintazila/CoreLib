/**
 * Copyright 2013 Mani Selvaraj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruint.core.utils.volley;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.mime.content.ByteArrayBody;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ruint.core.utils.preferences.CorePreferences;

/**
 * ruint.core.utils.volley.VolleyUtils MultipartRequest - To handle the large
 * file uploads. Extended from JSONRequest. You might want to change to
 * StringRequest based on your response type.
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:18:38
 * @param <T>
 */
public class MultiPartStringRequest<T> extends Request<T> implements MultiPartRequest {

  /**
   * Gson parser
   */
  private final Gson mGson;

  /**
   * Class type for the response
   */
  private final Class<T> mClass;
  private final Listener<T> mListener;
  /* To hold the parameter name and the File to upload */
  private Map<String, File> files = new HashMap<String, File>();

  /* To hold the parameter name and the string content to upload */
  private Map<String, String> params = new HashMap<String, String>();

  /* To hold the parameter name and the string content to upload */
  private Map<String, ByteArrayBody> bytes = new HashMap<String, ByteArrayBody>();

  /**
   * Creates a new request with the given method.
   * 
   * @param method
   *          the request {@link Method} to use
   * @param url
   *          URL to fetch the string at
   * @param listener
   *          Listener to receive the String response
   * @param errorListener
   *          Error listener, or null to ignore errors
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public MultiPartStringRequest(String url, Class objectClass, Listener<T> listener, ErrorListener errorListener) {
    super(Method.PUT, url, errorListener);
    this.mClass = objectClass;
    this.mListener = listener;
    mGson = new Gson();
  }

  @Override
  public void addFile(String param, File file) {
    files.put(param, file);
  }

  @Override
  public void addString(String param, String content) {
    params.put(param, content);
  }

  @Override
  public Map<String, File> getFile() {
    return files;
  }

  @Override
  public Map<String, String> getString() {
    return params;
  }

  @Override
  protected Response<T> parseNetworkResponse(NetworkResponse response) {
    /*
     * String parsed; try { parsed = new String(response.data,
     * HttpHeaderParser.parseCharset(response.headers)); } catch
     * (UnsupportedEncodingException e) { parsed = new String(response.data); }
     * return Response.success(parsed,
     * HttpHeaderParser.parseCacheHeaders(response));
     */
    CorePreferences.DEBUG("result:" + response);
    try {
      String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
      return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(response));
    } catch (UnsupportedEncodingException e) {
      return Response.error(new ParseError(e));
    } catch (JsonSyntaxException e) {
      return Response.error(new ParseError(e));
    }
  }

  @Override
  protected void deliverResponse(T response) {
    if (mListener != null) {
      mListener.onResponse(response);
    }
  }

  /**
   * 空表示不上传
   */
  public String getBodyContentType() {
    return null;
  }

  @Override
  public void addByte(String param, ByteArrayBody body) {
    bytes.put(param, body);
  }

  @Override
  public Map<String, ByteArrayBody> getByte() {
    return bytes;
  }
}