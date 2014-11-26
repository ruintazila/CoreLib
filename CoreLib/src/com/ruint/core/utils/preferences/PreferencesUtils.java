/*
 * PreferencesUtil.java
 * classes : ruint.core.utils.PreferencesUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午1:37:45
 */
package com.ruint.core.utils.preferences;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ruint.core.bean.CorePreferencesDTO;
import com.ruint.core.exception.ReflectException;
import com.ruint.core.exception.SharedPreferenceException;
import com.ruint.core.reflect.ReflectUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * ruint.core.utils.PreferencesUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午1:37:45
 */
public class PreferencesUtils {

  private final SharedPreferences mPrefs;

  public PreferencesUtils(Context context) {
    mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public SharedPreferences getSharedPreferences() {
    return mPrefs;
  }

  /**
   * 清空
   * 
   * @param type
   */
  public void clean(String key) {
    mPrefs.edit().remove(key).commit();
  }

  /**
   * 获取一个list对象，以jsonarray的方式返回
   * 
   * @param key
   * @return
   */
  public JSONArray getList(String key) {
    String oldInfo = mPrefs.getString(key, null);
    JSONArray oldInfoArray;
    if (oldInfo == null) {
      oldInfoArray = new JSONArray();
    } else {
      try {
        oldInfoArray = new JSONArray(oldInfo);
      } catch (JSONException e) {
        e.printStackTrace();
        oldInfoArray = new JSONArray();
      }
    }
    return oldInfoArray;
  }

  /**
   * 获取历史记录，返回转换完的对象
   * 
   * @param type
   * @return
   * @throws ReflectException
   * @throws JSONException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <T extends CorePreferencesDTO> List<T> getListWithCast(T t, String key) throws ReflectException, JSONException {
    JSONArray jsonArray = getList(key);
    List<T> list = new ArrayList<T>();
    T temp;
    for (int i = 0; i < jsonArray.length(); i++) {
      temp = (T) ReflectUtil.copy(t.getClass(), jsonArray.getJSONObject(i));
      list.add(temp);
    }
    return list;
  }

  /**
   * 删除某一个收藏
   * 
   */
  @SuppressLint("NewApi")
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <T extends CorePreferencesDTO> void removeListItem(String key, T t) {
    JSONArray infoArray = getList(key);
    int flag = -1;
    for (int i = 0; i < infoArray.length(); i++) {
      try {
        T temp = (T) ReflectUtil.copy(t.getClass(), infoArray.getJSONObject(i));
        if (t.isSame(temp)) {
          flag = i;
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (flag != -1) {
      infoArray.remove(flag);
      mPrefs.edit().putString(key, infoArray.toString()).commit();
    }

  }

  /**
   * 是否已经存在历史记录
   * 
   * @param type
   * @param houseInfo
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <T extends CorePreferencesDTO> boolean hasListItem(String key, T t) {
    JSONArray infoArray = getList(key);
    boolean flag = false;
    for (int i = 0; i < infoArray.length(); i++) {
      try {
        T temp = (T) ReflectUtil.copy(t.getClass(), infoArray.getJSONObject(i));
        if (t.isSame(temp)) {
          flag = true;
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return flag;
  }

  public void putBoolean(String key, boolean value) {
    mPrefs.edit().putBoolean(key, value).commit();
  }

  public void putInt(String key, int value) {
    mPrefs.edit().putInt(key, value).commit();
  }

  public void putString(String key, String value) {
    mPrefs.edit().putString(key, value).commit();
  }

  public void putFloat(String key, float value) {
    mPrefs.edit().putFloat(key, value).commit();
  }

  public void putLong(String key, long value) {
    mPrefs.edit().putLong(key, value).commit();
  }

  public boolean getBoolean(String key, boolean defValue) {
    return mPrefs.getBoolean(key, defValue);
  }

  public int getInt(String key, int defValue) {
    return mPrefs.getInt(key, defValue);
  }

  public String getString(String key, String defValue) {
    return mPrefs.getString(key, defValue);
  }

  public float getFloat(String key, float defValue) {
    return mPrefs.getFloat(key, defValue);
  }

  public long getLong(String key, long defValue) {
    return mPrefs.getLong(key, defValue);
  }

  @SuppressWarnings("unchecked")
  public <T> T getObject(String key, T t) throws SharedPreferenceException {
    try {
      return (T) ReflectUtil.copy(t.getClass(), new JSONObject(mPrefs.getString(key, "")));
    } catch (Exception e) {
      throw new SharedPreferenceException("get object occurs exception", e);
    }
  }
}
