/*
 * DataBaseOpenHelper.java
 * classes : com.ruint.core.cache.db.DataBaseOpenHelper
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午4:05:59
 */
package com.ruint.core.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * com.ruint.core.cache.db.DataBaseOpenHelper
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午4:05:59
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {
  private DataBaseOpenListener listener;

  public DataBaseOpenHelper(Context context, String dbname, int version, DataBaseOpenListener listener) {
    super(context, dbname, null, version);
    this.listener = listener;
  }

  public DataBaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)");

    if (listener.getCreateSql() != null) {
      for (int i = 0; i < listener.getCreateSql().length; i++) {
        db.execSQL(listener.getCreateSql()[i]);
      }
    }
    if (listener.getIndexSql() != null) {
      for (int i = 0; i < listener.getIndexSql().length; i++) {
        db.execSQL(listener.getIndexSql()[i]);
      }
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
    // db.execSQL("DROP TABLE IF EXISTS person");
    // onCreate(db);
    listener.onVersionUpgrade(db, arg1, arg2);
  }

  public interface DataBaseOpenListener {
    public String[] getCreateSql();

    public String[] getIndexSql();

    public void onVersionUpgrade(SQLiteDatabase db, int arg1, int arg2);
  }
}
