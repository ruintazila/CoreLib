/*
 * URLBuilder.java
 * classes : com.ruint.core.utils.URLBuilder
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-25 下午5:07:35
 */
package com.ruint.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.ruint.core.utils.preferences.CorePreferences;

/**
 * com.ruint.core.utils.URLBuilder
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 下午5:07:35
 */
public abstract class URLBuilder {
  private static final String CHARENCODE = HTTP.UTF_8;

  protected static String makeGetRequest(String header, String action, List<NameValuePair> pList) {
    return makeRequestHeader(header, action) + URLEncodedUtils.format(pList, CHARENCODE);
  }

  protected static String makeRequestHeader(String header, String action) {
    return header + action + ".action?";
  }

  protected static Map<String, String> makeMap(List<NameValuePair> pList) {
    return convertToParamMap(pList);
  }

  protected static List<NameValuePair> makeList(Map<String, String> params, List<NameValuePair> pList) {
    return addMapParams(params, pList);
  }

  private static Map<String, String> convertToParamMap(List<NameValuePair> pList) {
    Map<String, String> paramMap = new HashMap<String, String>();
    for (NameValuePair namevalue : pList) {
      paramMap.put(namevalue.getName(), namevalue.getValue());
    }
    return paramMap;
  }

  @SuppressWarnings("rawtypes")
  private static List<NameValuePair> addMapParams(Map<String, String> params, List<NameValuePair> pList) {
    if (params != null) {
      Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry entry = (Map.Entry) iterator.next();
        Object key = entry.getKey();
        Object val = entry.getValue();
        pList.add(new BasicNameValuePair(key.toString(), val.toString()));
        CorePreferences.DEBUG(key.toString() + "," + val.toString());
      }
    }
    return pList;
  }

  protected abstract void addCommonParams(List<NameValuePair> pList);

  protected abstract void addCommonParams(Map<String, String> params);
}
