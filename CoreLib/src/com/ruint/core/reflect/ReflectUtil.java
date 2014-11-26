/*
 * ReflectUtil.java
 * classes : ruint.core.reflect.ReflectUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午10:48:17
 */
package com.ruint.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ruint.core.exception.ReflectException;

/**
 * ruint.core.reflect.ReflectUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午10:48:17
 */
public class ReflectUtil {
  @SuppressWarnings("unused")
  private static final String TAG = "ReflectUtil";

  public static final String CLASS_SUFFIX = ".class";

  public static final String JAR_SUFFIX = ".jar";

  public static final String SEARCH_TYPE_FILE = "file";

  public static final String SEARCH_TYPE_JAR = "jar";

  /**
   * invoke a java instance's given method
   * 
   * @param owner
   *          ,a instance
   * @param methodName
   *          ,the method name
   * @param args
   *          , the arguments required by method
   * @return
   * @throws ReflectException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  @SuppressWarnings("rawtypes")
  public static Object invokeMethod(Object owner, String methodName, Object[] args) throws ReflectException {
    try {
      Class clazz = owner.getClass();
      Class[] argsClass = null;
      if (args != null) {
        argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
          argsClass[i] = args[i].getClass();
        }
      }
      @SuppressWarnings("unchecked")
      Method method = clazz.getDeclaredMethod(methodName, argsClass);
      if (isNeedSetAccessible(method.getModifiers())) {
        method.setAccessible(true);
      }
      return method.invoke(owner, args);
    } catch (Exception e) {
      throw new ReflectException("Invoke method occurs exception", e);
    }
  }

  /**
   * invoke a java instance's given method
   * 
   * @param owner
   *          ,a instance
   * @param methodName
   *          ,the method name
   * @param clazzs
   *          , the parameter types of method
   * @param args
   *          , the arguments required by method
   * @return
   * @throws ReflectException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  @SuppressWarnings("rawtypes")
  public static Object invokeMethod(Object owner, String methodName, Class[] clazzs, Object[] args)
      throws ReflectException {
    try {
      Class clazz = owner.getClass();
      @SuppressWarnings("unchecked")
      Method method = clazz.getDeclaredMethod(methodName, clazzs);
      if (isNeedSetAccessible(method.getModifiers())) {
        method.setAccessible(true);
      }
      return method.invoke(owner, args);
    } catch (Exception e) {
      throw new ReflectException("Invoke method occurs exception", e);
    }
  }

  /**
   * invoke a java class's static method
   * 
   * @param className
   *          ,the java class full path
   * @param methodName
   *          ,the static method name
   * @param clazzs
   *          , the parameter types of method
   * @param args
   *          ,the arguments required by method
   * @return
   * @throws ReflectException
   */
  @SuppressWarnings("rawtypes")
  public static Object invokeStaticMethod(String className, String methodName, Class[] clazzs, Object[] args)
      throws ReflectException {
    try {
      Class clazz = Class.forName(className);
      @SuppressWarnings("unchecked")
      Method method = clazz.getDeclaredMethod(methodName, clazz);
      if (isNeedSetAccessible(method.getModifiers())) {
        method.setAccessible(true);
      }
      return method.invoke(null, args);
    } catch (Exception e) {
      throw new ReflectException("Invoke static method occurs exception", e);
    }
  }

  /**
   * invoke a java class's static method
   * 
   * @param className
   *          ,the java class full path
   * @param methodName
   *          ,the static method name
   * @param args
   *          ,the arguments required by method
   * @return
   * @throws ReflectException
   */
  @SuppressWarnings("rawtypes")
  public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws ReflectException {
    try {
      Class clazz = Class.forName(className);
      Class[] argsClass = null;
      if (args != null) {
        argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
          argsClass[i] = args[i].getClass();
        }
      }
      @SuppressWarnings("unchecked")
      Method method = clazz.getDeclaredMethod(methodName, argsClass);
      if (isNeedSetAccessible(method.getModifiers())) {
        method.setAccessible(true);
      }
      return method.invoke(null, args);
    } catch (Exception e) {
      throw new ReflectException("Invoke static method occurs exception", e);
    }
  }

  /**
   * query the instance's method by method name and the number of parameter
   * 
   * @param owner
   * @param methodName
   * @param paramsNumber
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static Method getMethod(Object owner, String methodName, int paramsNumber) {
    Method rMethod = null;
    Class clazz = owner.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        if (method.getGenericParameterTypes().length == paramsNumber) {
          rMethod = method;
          break;
        }
      }
    }
    return rMethod;
  }

  @SuppressWarnings("rawtypes")
  public static Method getMethod(Class clazz, String methodName, int paramsNumber) {
    Method rMethod = null;
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        if (method.getGenericParameterTypes().length == paramsNumber) {
          rMethod = method;
          break;
        }
      }
    }
    return rMethod;
  }

  // public Object[] cast
  /**
   * new a java class's instance
   * 
   * @param className
   *          ,the java class full path
   * @param args
   *          ,the arguments required by constructor
   * @return
   * @throws ReflectException
   */
  @SuppressWarnings("rawtypes")
  public static Object newInstance(String className, Object[] args) throws Exception {
    Class clazz = Class.forName(className);
    return newInstance(clazz, args);
  }

  /**
   * new a java class's instance
   * 
   * @param Class
   *          ,the java class
   * @param args
   *          ,the arguments required by constructor
   * @return
   * @throws ReflectException
   */
  @SuppressWarnings("rawtypes")
  public static Object newInstance(Class clazz, Object[] args) throws ReflectException {
    try {
      Class[] argsClass = null;
      if (args != null) {
        argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
          argsClass[i] = args[i].getClass();
        }
      }
      @SuppressWarnings("unchecked")
      Constructor constructor = clazz.getDeclaredConstructor(argsClass);
      if (isNeedSetAccessible(constructor.getModifiers())) {
        constructor.setAccessible(true);
      }
      return constructor.newInstance();
    } catch (Exception e) {
      throw new ReflectException("New instance occurs exception", e);
    }

  }

  /**
   * judge whether need to set the accessible attribute
   * 
   * @param modefiers
   * @return
   */
  private static boolean isNeedSetAccessible(int modefiers) {
    if (!Modifier.isPublic(modefiers)) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("rawtypes")
  public static Object[] castDataType(Class[] clazzs, String[] objects) throws ReflectException {
    if (clazzs.length != objects.length) {
      return null;
    }
    Object[] rObjects = new Object[objects.length];
    for (int i = 0; i < clazzs.length; i++) {
      rObjects[i] = getObjectFromString(clazzs[i], objects[i]);
    }
    return rObjects;
  }

  @SuppressWarnings("rawtypes")
  public static boolean isSimpleDataType(Class clazz) {
    if (clazz == boolean.class || clazz == char.class || clazz == byte.class || clazz == short.class
        || clazz == int.class || clazz == long.class || clazz == float.class || clazz == double.class
        || clazz == Boolean.class || clazz == Character.class || clazz == Byte.class || clazz == Short.class
        || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class
        || clazz == String.class) {
      return true;
    }
    return false;
  }

  public static boolean isArrayDataType(Object o) {
    if ((o instanceof boolean[]) || (o instanceof char[]) || (o instanceof byte[]) || (o instanceof short[])
        || (o instanceof int[]) || (o instanceof long[]) || (o instanceof float[]) || (o instanceof double[])
        || (o instanceof Boolean[]) || (o instanceof Character[]) || (o instanceof Byte[]) || (o instanceof Short[])
        || (o instanceof Integer[]) || (o instanceof Long[]) || (o instanceof Float[]) || (o instanceof Double[])
        || (o instanceof String[]) || (o instanceof Collection)) {
      return true;
    }
    return false;
  }

  public static boolean isCollectionDataType(Object o) {
    if (o instanceof Collection) {
      return true;
    }
    return false;
  }

  /**
   * judge whether list type
   * 
   * @param clazz
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static boolean isListType(Class clazz) {
    if (List.class.isAssignableFrom(clazz)) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("rawtypes")
  public static Class getObjectClass(Class clazz) {
    Class rClazz = clazz;
    if (clazz == boolean.class) {
      rClazz = Boolean.class;
    } else if (clazz == char.class) {
      rClazz = Character.class;
    } else if (clazz == byte.class) {
      rClazz = Byte.class;
    } else if (clazz == short.class) {
      rClazz = Short.class;
    } else if (clazz == int.class) {
      rClazz = Integer.class;
    } else if (clazz == long.class) {
      rClazz = Long.class;
    } else if (clazz == float.class) {
      rClazz = Float.class;
    } else if (clazz == double.class) {
      rClazz = Double.class;
    }
    return rClazz;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Object getObjectFromString(Field field, String s) throws ReflectException {
    Class clazz = field.getType();
    Object obj = s;
    if (clazz == boolean.class || clazz == Boolean.class) {
      try {
        obj = Boolean.parseBoolean(s);
      } catch (Exception e) {
        obj = false;
      }
    } else if (clazz == char.class || clazz == Character.class) {
      try {
        obj = s.charAt(0);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == byte.class || clazz == Byte.class) {
      try {
        obj = Byte.parseByte(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == short.class || clazz == Short.class) {
      try {
        obj = Short.parseShort(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == int.class || clazz == Integer.class) {
      try {
        obj = Integer.parseInt(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == long.class || clazz == Long.class) {
      try {
        obj = Long.parseLong(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == float.class || clazz == Float.class) {
      try {
        obj = Float.parseFloat(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == double.class || clazz == Double.class) {
      try {
        obj = Double.parseDouble(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == String.class) {
      if (s == null || "".equals(s) || "null".equalsIgnoreCase(s)) {
        obj = null;
      } else {
        obj = s;
      }
    } else if (isListType(clazz)) {
      // just support List interface,only instance a ArrayList to put data
      obj = new ArrayList();
      // get the generic type of List
      Class genericClass = getFieldGenericType(field);
      // json array
      try {
        JSONArray jsonArray = new JSONArray(s);
        boolean flag = isSimpleDataType(genericClass);
        for (int i = 0; i < jsonArray.length(); i++) {
          ((List) obj).add(flag ? getObjectFromString(genericClass, jsonArray.getString(i)) : copy(genericClass,
              jsonArray.getJSONObject(i)));

        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } else {
      // complex Object,the input String is JSON String
      try {
        JSONObject jsonObject = new JSONObject(s);
        obj = copy(clazz, jsonObject);
      } catch (JSONException e) {
        obj = null;
      }

    }
    return obj;
  }

  /**
   * cast string to a Object by given Class,not support List or Array data type
   * and etc
   * 
   * @param clazz
   * @param s
   * @return
   * @throws ReflectException
   */
  @SuppressWarnings("rawtypes")
  public static Object getObjectFromString(Class clazz, String s) throws ReflectException {
    Object obj = s;
    if (clazz == boolean.class || clazz == Boolean.class) {
      try {
        obj = Boolean.parseBoolean(s);
      } catch (Exception e) {
        obj = false;
      }
    } else if (clazz == char.class || clazz == Character.class) {
      try {
        obj = s;
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == byte.class || clazz == Byte.class) {
      try {
        obj = Byte.parseByte(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == short.class || clazz == Short.class) {
      try {
        obj = Short.parseShort(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == int.class || clazz == Integer.class) {
      try {
        obj = Integer.parseInt(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == long.class || clazz == Long.class) {
      try {
        obj = Long.parseLong(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == float.class || clazz == Float.class) {
      try {
        obj = Float.parseFloat(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == double.class || clazz == Double.class) {
      try {
        obj = Double.parseDouble(s);
      } catch (Exception e) {
        obj = -1;
      }
    } else if (clazz == String.class) {
      if (s == null || "".equals(s) || "null".equalsIgnoreCase(s)) {
        obj = null;
      } else {
        obj = s;
      }
    } else {
      // complex Object,the input String is JSON String
      // TODO support multi-type String data
      try {
        JSONObject jsonObject = new JSONObject(s);
        obj = copy(clazz, jsonObject);
      } catch (JSONException e) {
        obj = null;
      }

    }
    return obj;
  }

  /**
   * copy the attributes from JSON Object to Class we'd better just use to copy
   * basic attribute, the recursion support is unchecked.
   * 
   * @param clazz
   * @param json
   * @return
   * @throws Exception
   */
  @SuppressWarnings("rawtypes")
  public static Object copy(Class clazz, JSONObject json) throws ReflectException {
    Object o;
    try {
      o = newInstance(clazz, null);
      Field[] fields = clazz.getDeclaredFields();
      String fieldName, sValue;
      Object fieldValue;
      for (Field field : fields) {
        if (isNeedSetAccessible(field.getModifiers())) {
          field.setAccessible(true);
        }
        field.setAccessible(true);
        fieldName = field.getName();
        if (json.has(fieldName)) {
          sValue = json.get(fieldName).toString();
          fieldValue = getObjectFromString(field, sValue);
          try {
            field.set(o, fieldValue);
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
          }
        }
      }
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      throw new ReflectException("Json data parse error:", e);
    }
    return o;
  }

  @SuppressWarnings("rawtypes")
  public static Field getField(Class clazz, String varName) {
    Field[] fields = clazz.getDeclaredFields();
    String fieldName;
    for (Field field : fields) {
      if (isNeedSetAccessible(field.getModifiers())) {
        field.setAccessible(true);
      }
      fieldName = field.getName();
      if (varName.endsWith(fieldName)) {
        return field;
      }
    }
    return null;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T> T cast(Class clazz, Object o) {
    return (T) clazz.cast(o);
  }

  @SuppressWarnings("rawtypes")
  public static boolean isExtendsOf(Class clazz, Class parent) {
    boolean flag = false;
    Class t = clazz.getSuperclass();
    while (t != null) {
      if (t == parent) {
        flag = true;
        break;
      }
      t = t.getSuperclass();
    }
    return flag;
  }

  @SuppressWarnings("rawtypes")
  public static Method[] getMethod(Class clazz, int modifier) {
    List<Method> methods = new ArrayList<Method>();
    for (Method method : clazz.getDeclaredMethods()) {
      if ((method.getModifiers() & modifier) > 0) {
        methods.add(method);
      }

    }
    return methods.toArray(new Method[methods.size()]);

  }

  @SuppressWarnings({ "rawtypes" })
  public static Class getSuperClassGenricType(Class clazz, int index) {
    Type genType = clazz.getGenericSuperclass();// �õ����͸���
    if (!(genType instanceof ParameterizedType)) {
      return Object.class;
    }
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if (index >= params.length || index < 0) {
      throw new RuntimeException("RuntimeException" + (index < 0 ? "index < 0" : "index out of params.length"));
    }
    if (!(params[index] instanceof Class)) {
      return Object.class;
    }
    return (Class) params[index];
  }

  @SuppressWarnings({ "rawtypes" })
  public static Class getSuperClassGenricType(Class clazz) {
    return getSuperClassGenricType(clazz, 0);
  }

  @SuppressWarnings("rawtypes")
  public static Class getMethodGenericReturnType(Method method, int index) {
    Type returnType = method.getGenericReturnType();
    if (returnType instanceof ParameterizedType) {
      ParameterizedType type = (ParameterizedType) returnType;
      Type[] typeArguments = type.getActualTypeArguments();
      if (index >= typeArguments.length || index < 0) {
        throw new RuntimeException("RuntimeException" + (index < 0 ? "index < 0" : "index out of typeArguments.length"));
      }
      return (Class) typeArguments[index];
    }
    return Object.class;
  }

  @SuppressWarnings({ "rawtypes" })
  public static Class getMethodGenericReturnType(Method method) {
    return getMethodGenericReturnType(method, 0);
  }

  @SuppressWarnings("rawtypes")
  public static List<Class> getMethodGenericParameterTypes(Method method, int index) {
    List<Class> results = new ArrayList<Class>();
    Type[] genericParameterTypes = method.getGenericParameterTypes();
    if (index >= genericParameterTypes.length || index < 0) {
      throw new RuntimeException("RuntimeException"
          + (index < 0 ? "index < 0" : "index out of genericParameterTypes.length"));
    }
    Type genericParameterType = genericParameterTypes[index];
    if (genericParameterType instanceof ParameterizedType) {
      ParameterizedType aType = (ParameterizedType) genericParameterType;
      Type[] parameterArgTypes = aType.getActualTypeArguments();
      for (Type parameterArgType : parameterArgTypes) {
        Class parameterArgClass = (Class) parameterArgType;
        results.add(parameterArgClass);
      }
      return results;
    }
    return results;
  }

  @SuppressWarnings("rawtypes")
  public static List<Class> getMethodGenericParameterTypes(Method method) {
    return getMethodGenericParameterTypes(method, 0);
  }

  @SuppressWarnings("rawtypes")
  public static Class getFieldGenericType(Field field, int index) {
    Type genericFieldType = field.getGenericType();

    if (genericFieldType instanceof ParameterizedType) {
      ParameterizedType aType = (ParameterizedType) genericFieldType;
      Type[] fieldArgTypes = aType.getActualTypeArguments();
      if (index >= fieldArgTypes.length || index < 0) {
        throw new RuntimeException("RuntimeException" + (index < 0 ? "index < 0" : "index out of fieldArgTypes.length"));
      }
      return (Class) fieldArgTypes[index];
    }
    return Object.class;
  }

  @SuppressWarnings("rawtypes")
  public static Class getFieldGenericType(Field field) {
    return getFieldGenericType(field, 0);
  }

}
