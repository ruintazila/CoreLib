/*
 * CacheDBService.java
 * classes : com.ruint.core.cache.db.CacheDBService
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午4:04:01
 */
package com.ruint.core.cache.db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ruint.core.bean.CorePreferencesDTO;
import com.ruint.core.cache.db.DataBaseOpenHelper.DataBaseOpenListener;
import com.ruint.core.cache.db.DataBaseService.DBQuery;
import com.ruint.core.reflect.ReflectUtil;
import com.ruint.core.utils.preferences.CorePreferences;
import com.ruint.core.utils.text.TextUtil;

/**
 * com.ruint.core.cache.db.CacheDBService
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午4:04:01
 */
public class CacheDBService {
  private final int VERSION = 1;
  private final String DB_NAME = "tf_cache";
  private final String TABLE_NAME = "table_cache";

  private DataBaseService dbService;
  private static CacheDBService instance;

  public CacheDBService(Context context) {
    dbService = new DataBaseService(context, DB_NAME, VERSION, new DataBaseOpenListener() {

      @Override
      public void onVersionUpgrade(SQLiteDatabase db, int arg1, int arg2) {

      }

      @Override
      public String[] getIndexSql() {
        return null;
      }

      @Override
      public String[] getCreateSql() {
        String[] createSql = new String[1];
        createSql[0] = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(cahce_key text UNIQUE,cache_value text)";
        return createSql;
      }
    });
  }

  public static CacheDBService getInstance(Context context) {
    if (null == instance) {
      instance = new CacheDBService(context);
    }

    return instance;
  }

  public void addCache(String key, String value) {
    DataBaseOpenHelper dbo = dbService.getDbOpenHelper();
    SQLiteDatabase db = dbo.getWritableDatabase();
    db.execSQL("replace into " + TABLE_NAME + "(cahce_key, cache_value) values(?,?)",
        new Object[] { TextUtil.getUTF8(key), TextUtil.getUTF8(value) });
  }

  public String getCache(String key) {
    return (String) dbService.query("select cache_value from " + TABLE_NAME + " where cahce_key = '" + key + "'", null,
        new DBQuery() {

          @Override
          public Object onQueryResult(Cursor cursor) {
            String value = null;
            if (cursor.moveToNext()) {
              value = cursor.getString(0);
            }
            cursor.close();
            return value;
          }
        });
  }

  public void deleteCache(String key) {
    DataBaseOpenHelper dbo = dbService.getDbOpenHelper();
    SQLiteDatabase db = dbo.getWritableDatabase();
    db.delete(TABLE_NAME, "cahce_key=?", new String[] { key });
  }

  public void clearCache() {
    DataBaseOpenHelper dbo = dbService.getDbOpenHelper();
    SQLiteDatabase db = dbo.getWritableDatabase();
    db.execSQL("delete from " + TABLE_NAME, new String[] {});
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T extends CorePreferencesDTO> boolean hasListItem(String key, T t) {
    boolean flag = false;
    try {
      JSONArray infoArray = getList(key);

      for (int i = 0; i < infoArray.length(); i++) {
        T temp = (T) ReflectUtil.copy(t.getClass(), infoArray.getJSONObject(i));
        if (t.isSame(temp)) {
          flag = true;
          break;
        }
      }
    } catch (Exception e) {
      CorePreferences.ERROR(e);
    }
    return flag;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T extends CorePreferencesDTO> List<T> getListWithCast(T t, String key) {
    List<T> list = new ArrayList<T>();
    try {
      JSONArray jsonArray = getList(key);
      T temp;
      for (int i = 0; i < jsonArray.length(); i++) {
        temp = (T) ReflectUtil.copy(t.getClass(), jsonArray.getJSONObject(i));
        list.add(temp);
      }
    } catch (Exception e) {
      CorePreferences.ERROR(e);
    }

    return list;
  }

  @SuppressLint("NewApi")
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T extends CorePreferencesDTO> void removeListItem(String key, T t) {
    try {
      JSONArray infoArray = getList(key);
      int flag = -1;
      for (int i = 0; i < infoArray.length(); i++) {
        T temp = (T) ReflectUtil.copy(t.getClass(), infoArray.getJSONObject(i));
        if (t.isSame(temp)) {
          flag = i;
          break;
        }
      }
      if (flag != -1) {
        infoArray.remove(flag);
        addCache(key, infoArray.toString());
      }
    } catch (Exception e) {
      CorePreferences.ERROR(e);
    }
  }

  @SuppressWarnings("rawtypes")
  public void putList(String key, List list) {
    addCache(key, new JSONArray(list).toString());
  }

  public JSONArray getList(String key) {
    JSONArray oldInfoArray;
    try {
      String oldInfo = getCache(key);
      if (oldInfo == null) {
        oldInfoArray = new JSONArray();
      } else {
        oldInfoArray = new JSONArray(oldInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
      oldInfoArray = new JSONArray();
    }
    return oldInfoArray;
  }
}
