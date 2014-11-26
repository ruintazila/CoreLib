/*
 * BaseBean.java
 * classes : ruint.core.bean.BaseBean
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午10:43:17
 */
package com.ruint.core.bean;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.ruint.core.exception.ReflectException;
import com.ruint.core.reflect.ReflectUtil;

import android.database.Cursor;

/**
 * ruint.core.bean.BaseBean
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午10:43:17
 */
public class BaseBean implements Serializable {

  @SuppressWarnings("unused")
  private static final String TAG = "BaseBean";

  private static final long serialVersionUID = -1316742234371607183L;

  public static boolean isContainColumn(String colName, Cursor rs) {

    boolean flag = false;
    for (String s : rs.getColumnNames()) {
      if (s.equalsIgnoreCase(colName)) {
        flag = true;
        break;
      }
    }
    return flag;
  }

  @SuppressWarnings("rawtypes")
  public void readFromCursor(Cursor cursor) {
    Class clazz = this.getClass();
    Field[] fields = clazz.getDeclaredFields();
    String name;
    for (Field f : fields) {
      f.setAccessible(true);
      name = f.getName();
      if (isContainColumn(name, cursor)) {
        int idx = cursor.getColumnIndex(name);
        String s = cursor.getString(idx);
        if (s != null) {
          if (ReflectUtil.isSimpleDataType(f.getType())) {
            try {
              f.set(this, ReflectUtil.getObjectFromString(f.getType(), s));
            } catch (IllegalArgumentException e) {
              e.printStackTrace();
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (ReflectException e) {
              e.printStackTrace();
            }
          }
        }

      }

    }
  }
}
