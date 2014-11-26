/*
 * DataBaseService.java
 * classes : com.ruint.core.cache.db.DataBaseService
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午4:05:16
 */
package com.ruint.core.cache.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ruint.core.cache.db.DataBaseOpenHelper.DataBaseOpenListener;

/**
 * com.ruint.core.cache.db.DataBaseService
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午4:05:16
 */
public class DataBaseService {

  private DataBaseOpenHelper dbOpenHelper;

  public DataBaseService() {

  }

  public DataBaseService(Context context, String dbname, int version, DataBaseOpenListener listener) {
    dbOpenHelper = new DataBaseOpenHelper(context, dbname, version, listener);
  }

  public void execute(String sql, Object[] values) {
    SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
    database.execSQL(sql, values);
  }

  public Object query(String sql, String[] values, DBQuery dbQuery) {
    SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
    Cursor cursor = database.rawQuery(sql, values);
    Object o = dbQuery.onQueryResult(cursor);
    cursor.close();
    return o;
  }

  public long getCount(String sql) {
    SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
    Cursor cursor = database.rawQuery(sql, null);
    if (cursor.moveToNext()) {
      return cursor.getLong(0);
    }
    return 0;
  }

  public interface DBQuery {
    public Object onQueryResult(Cursor cursor);
  }

  public DataBaseOpenHelper getDbOpenHelper() {
    return dbOpenHelper;
  }

  public void destory() {
    dbOpenHelper.close();
  }
}
