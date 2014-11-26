/*
 * MD5Util.java
 * classes : com.ruint.core.utils.MD5Util
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:40:59
 */
package com.ruint.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.text.TextUtils;

/**
 * com.ruint.core.utils.MD5Util
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:40:59
 */
public class MD5Utils {
  public final static String MD5_ARITHMETIC_NAME = "MD5";
  public final static String appMD5SecretKey = "ruint";// 默认密钥

  private static MD5Utils mD5Util;

  // 全局数组
  private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
      "e", "f" };

  private MD5Utils() {
  }

  // 返回形式为数字跟字符串
  private static String byteToArrayString(byte bByte) {
    int iRet = bByte;
    if (iRet < 0) {
      iRet += 256;
    }
    int iD1 = iRet / 16;
    int iD2 = iRet % 16;
    return strDigits[iD1] + strDigits[iD2];
  }

  // 返回形式只为数字
  @SuppressWarnings("unused")
  private static String byteToNum(byte bByte) {
    int iRet = bByte;
    System.out.println("iRet1=" + iRet);
    if (iRet < 0) {
      iRet += 256;
    }
    return String.valueOf(iRet);
  }

  // 转换字节数组为16进制字串
  private static String byteToString(byte[] bByte) {
    StringBuffer sBuffer = new StringBuffer();
    for (int i = 0; i < bByte.length; i++) {
      sBuffer.append(byteToArrayString(bByte[i]));
    }
    return sBuffer.toString();
  }

  public static String GetMD5Code(String strObj, String secretKey) {
    String resultString = null;
    if (TextUtils.isEmpty(secretKey)) {
      secretKey = appMD5SecretKey;
    }
    try {
      resultString = new String(strObj + secretKey);
      MessageDigest md = MessageDigest.getInstance("MD5");
      // md.digest() 该函数返回值为存放哈希值结果的byte数组
      resultString = byteToString(md.digest(resultString.getBytes()));
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return resultString;
  }

  public static String GetMD5Code(String strObj) {
    String resultString = null;

    try {
      resultString = new String(strObj);
      MessageDigest md = MessageDigest.getInstance("MD5");
      // md.digest() 该函数返回值为存放哈希值结果的byte数组
      resultString = byteToString(md.digest(resultString.getBytes()));
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return resultString;
  }

  public static MD5Utils getInstance() {
    if (mD5Util == null) {
      return mD5Util = new MD5Utils();
    }
    return mD5Util;
  }
}
