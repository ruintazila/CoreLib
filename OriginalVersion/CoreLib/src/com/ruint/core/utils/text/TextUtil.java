/*
 * TextUtil.java
 * classes : com.ruint.core.utils.text.TextUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午4:11:48
 */
package com.ruint.core.utils.text;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ruint.core.utils.HtmlUtils;

/**
 * com.ruint.core.utils.text.TextUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午4:11:48
 */
public class TextUtil {
  public static String getString(Context context, int id, Object... formatArgs) {
    return context.getResources().getString(id, formatArgs);
  }

  public static String substring(String str, int toCount, String more) {
    int reInt = 0;
    String reStr = "";
    if (str == null)
      return "";
    char[] tempChar = str.toCharArray();
    for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
      String s1 = String.valueOf(tempChar[kk]);
      byte[] b = s1.getBytes();
      reInt += b.length;
      reStr += tempChar[kk];
    }
    if (toCount == reInt || (toCount == reInt - 1))
      reStr += more;
    return reStr;
  }

  @SuppressLint("SimpleDateFormat")
  public static String getRandString(int len) {
    StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    StringBuffer sb = new StringBuffer();
    Random r = new Random();
    int range = buffer.length();
    for (int i = 0; i < len; i++) {
      sb.append(buffer.charAt(r.nextInt(range)));
    }
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
    String strCreateTime = fmt.format(new Date().getTime());
    return strCreateTime + sb.toString();
  }


  public static boolean isVerifyUserName(String username) {
    if (username == null) {
      return false;
    } else if (username.length() < 3) {
      return false;
    } else if (isEmail(username) || isMobliePhone(username)) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isVerifyPassWord(String password) {
    if (password == null) {
      return false;
    } else if (password.length() < 6) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean isEmail(String str) {
    Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    return pattern.matcher(str).matches();
  }

  public static boolean isMobliePhone(String mobiles) {
    if (!TextUtils.isEmpty(mobiles)) {
      Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$");
      Matcher m = p.matcher(mobiles);
      return m.matches();
    } else {
      return false;
    }
  }

  public static String cutstr(String text, int length, String encode) {
    if (text == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    int currentLength = 0;
    for (char c : text.toCharArray()) {
      try {
        currentLength += String.valueOf(c).getBytes(encode).length;
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      if (currentLength <= length) {
        sb.append(c);
      } else {
        break;
      }
    }
    return sb.toString();
  }

  public static String cutstr(String text, int length) {
    if (text == null) {
      return null;
    }
    String temptext = HtmlUtils.filterHtml(text);
    return substring(temptext, length, "...");
  }

  public static String Html2Text(String inputString) {
    String htmlStr = inputString; 
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    java.util.regex.Pattern p_html1;
    java.util.regex.Matcher m_html1;
    try {
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
      String regEx_html = "<[^>]+>"; 
      String regEx_html1 = "<[^>]+";
      p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
      m_script = p_script.matcher(htmlStr);
      htmlStr = m_script.replaceAll("");
      p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
      m_style = p_style.matcher(htmlStr);
      htmlStr = m_style.replaceAll(""); 
      p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
      m_html = p_html.matcher(htmlStr);
      htmlStr = m_html.replaceAll(""); 
      p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
      m_html1 = p_html1.matcher(htmlStr);
      htmlStr = m_html1.replaceAll("");
      textStr = htmlStr;
    } catch (Exception e) {
    }
    return textStr;
  }

  public static String getUTF8StringFromGBKString(String gbkStr) {
    try {
      return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new InternalError();
    }
  }

  public static String getUTF8(String gbkStr) {
    if (gbkStr != null) {
      try {
        return new String(gbkStr.getBytes("utf-8"));
        // return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        // throw new InternalError();
      }
    }
    return gbkStr;
  }

  public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
    int n = gbkStr.length();
    byte[] utfBytes = new byte[3 * n];
    int k = 0;
    for (int i = 0; i < n; i++) {
      int m = gbkStr.charAt(i);
      if (m < 128 && m >= 0) {
        utfBytes[k++] = (byte) m;
        continue;
      }
      utfBytes[k++] = (byte) (0xe0 | (m >> 12));
      utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
      utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
    }
    if (k < utfBytes.length) {
      byte[] tmp = new byte[k];
      System.arraycopy(utfBytes, 0, tmp, 0, k);
      return tmp;
    }
    return utfBytes;
  }

  public static Integer strToInt(String str) {
    if (!TextUtils.isEmpty(str)) {
      return Integer.parseInt(str);
    } else {
      return 0;
    }
  }

  public static void setNullText(String temp, String str, TextView v) {
    if (temp == null || temp.equals(" ") || TextUtils.isEmpty(temp)) {
      v.setVisibility(View.GONE);
    } else {
      v.setVisibility(View.VISIBLE);
      v.setText(str);
    }
  }

  public static void setNullText(String temp, TextView v) {
    if (temp == null || temp.equals(" ") || TextUtils.isEmpty(temp)) {
      v.setVisibility(View.GONE);
    } else {
      v.setVisibility(View.VISIBLE);
      v.setText(temp);
    }
  }

  public static String[] arrayToShift(String[] tempArray) {
    if (tempArray != null && tempArray.length > 1) {
      final String[] chooseItems = new String[tempArray.length - 1];
      for (int i = 0; i < tempArray.length; i++) {
        if (i > 0) {
          chooseItems[i - 1] = (String) tempArray[i];
        }
      }
      return chooseItems;
    } else {
      return null;
    }
  }

  @SuppressLint("NewApi")
  public static void copy(Context context, String content) {
    ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    cmb.setPrimaryClip(ClipData.newPlainText(null, content.trim()));
  }
}
