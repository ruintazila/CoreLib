/*
 * FileUtil.java
 * classes : com.ruint.core.utils.FileUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:29:45
 */
package com.ruint.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ruint.core.utils.preferences.CorePreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import biz.source_code.base64Coder.Base64Coder;

/**
 * com.ruint.core.utils.FileUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:29:45
 */
public class FileUtils {
  public static boolean isSDCARDMounted() {
    return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
  }

  public static boolean isExistFile(String filename) {
    File file = new File(filename);
    if (!file.exists()) {
      return false;
    }
    return true;
  }

  public static String convertStreamToString(InputStream is) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }

  public static String getFileBase64String(File file) throws IOException {
    byte[] data = null;

    try {
      InputStream in = new FileInputStream(file);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Base64Coder.encodeLines(data);
  }

  public static String getFileNameFromUrl(String s) {
    int i = s.lastIndexOf("\\");
    if (i < 0 || i >= s.length() - 1) {
      i = s.lastIndexOf("/");
      if (i < 0 || i >= s.length() - 1)
        return s;
    }
    return s.substring(i + 1);
  }

  @SuppressLint("DefaultLocale")
  public static String getMIMEType(File f) {
    String type = "";
    String fName = f.getName();
    String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
    if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg")
        || end.equals("wav")) {
      type = "audio";
    } else if (end.equals("3gp") || end.equals("mp4")) {
      type = "video";
    } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
      type = "image";
    } else if (end.equals("apk")) {
      type = "application/vnd.android.package-archive";
    } else if (end.equals("doc") || end.equals("docx")) {
      type = "application/msword";
    } else if (end.equals("xls") || end.equals("xlsx")) {
      type = "application/vnd.ms-excel";
    } else if (end.equals("ppt") || end.equals("pptx")) {
      type = "application/vnd.ms-powerpoint";
    } else if (end.equals("pdf")) {
      type = "application/pdf";
    } else {
      type = "*";
    }
    if (type.indexOf("/") == -1) {
      type += "/*";
    }
    return type;
  }

  public static void chmod(String permission, String path) {
    try {
      String command = "chmod " + permission + " " + path;
      Runtime runtime = Runtime.getRuntime();
      runtime.exec(command);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void deleteFile(String filename) {
    File myFile = new File(filename);
    if (myFile.exists()) {
      myFile.delete();
    }
  }

  public static String getExtensionName(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot > -1) && (dot < (filename.length() - 1))) {
        return filename.substring(dot + 1);
      }
    }
    return filename;
  }

  public static String getFileNameNoEx(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot > -1) && (dot < (filename.length()))) {
        return filename.substring(0, dot);
      }
    }
    return filename;
  }

  public static String convertFileSize(long filesize) {
    String strUnit = "B";
    String strAfterComma = "";
    int intDivisor = 1;
    if (filesize >= 1024 * 1024 * 1024) {
      strUnit = "GB";
      intDivisor = 1024 * 1024 * 1024;
    } else if (filesize >= 1024 * 1024) {
      strUnit = "MB";
      intDivisor = 1024 * 1024;
    } else if (filesize >= 1024) {
      strUnit = "KB";
      intDivisor = 1024;
    }
    if (intDivisor == 1)
      return filesize + " " + strUnit;
    strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor;
    if (strAfterComma == "")
      strAfterComma = ".0";
    return filesize / intDivisor + "." + strAfterComma + " " + strUnit;
  }

  public static double getDirSize(File file) {
    if (file.exists()) {
      if (file.isDirectory()) {
        File[] children = file.listFiles();
        double size = 0;
        for (File f : children)
          size += getDirSize(f);
        return size;
      } else {
        double size = (double) file.length();
        return size;
      }
    } else {
      return 0.0;
    }
  }

  @SuppressLint("NewApi")
  @SuppressWarnings({ "unused" })
  public static long getSDCardSize(String path) {
    File pathFile = new File(path);
    android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
    long nTotalBlocks = statfs.getBlockCountLong();
    long nBlocSize = statfs.getBlockSizeLong();
    long nAvailaBlock = statfs.getAvailableBlocksLong();
    long nFreeBlock = statfs.getFreeBlocksLong();
    long nSDTotalSize = nTotalBlocks * nBlocSize / 1024 / 1024;
    return nSDTotalSize;
  }

  public static void delFolder(String folderPath) {
    try {
      delAllFile(folderPath); 
      String filePath = folderPath;
      filePath = filePath.toString();
      java.io.File myFilePath = new java.io.File(filePath);
      myFilePath.delete(); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean delAllFile(String path) {
    boolean flag = false;
    File file = new File(path);
    if (!file.exists()) {
      return flag;
    }
    if (!file.isDirectory()) {
      return flag;
    }
    String[] tempList = file.list();
    File temp = null;
    for (int i = 0; i < tempList.length; i++) {
      if (path.endsWith(File.separator)) {
        temp = new File(path + tempList[i]);
      } else {
        temp = new File(path + File.separator + tempList[i]);
      }
      if (temp.isFile()) {
        temp.delete();
      }
      if (temp.isDirectory()) {
        delAllFile(path + "/" + tempList[i]);
        delFolder(path + "/" + tempList[i]);
        flag = true;
      }
    }
    return flag;
  }

  public static String getFileFromUri(final Context context, final Uri uri, String tempfile) {

    final boolean isKitKat = Build.VERSION.SDK_INT >= 19;// Build.VERSION_CODES.KITKAT;
    if (isKitKat) {
      try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        FileOutputStream out = new FileOutputStream(tempfile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        out.flush();
        out.close();
      } catch (Exception e) {
        e.printStackTrace();
        tempfile = null;
      }
      return tempfile;
    }
    // MediaStore (and general)
    else if ("content".equalsIgnoreCase(uri.getScheme())) {
      return getDataColumn(context, uri, null, null);
    }
    // File
    else if ("file".equalsIgnoreCase(uri.getScheme())) {
      return uri.getPath();
    }
    return null;
  }

  public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
    Cursor cursor = null;
    final String column = MediaStore.Images.Media.DATA;
    final String[] projection = { column };
    try {
      cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
      if (cursor != null && cursor.moveToFirst()) {
        final int column_index = cursor.getColumnIndexOrThrow(column);
        return cursor.getString(column_index);
      }
    } finally {
      if (cursor != null)
        cursor.close();
    }
    return null;
  }

  public static File getTempFile(Context context, Uri uri) {
    File file = null;
    try {
      Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
      if (null != bm) {
        file = saveImage(bm);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return file;
  }

  public static File saveImage(Bitmap bitmap) {
    File file = createTempFile();
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(file);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }

    return file;
  }

  public static File createTempFile() {
    String filename = System.currentTimeMillis() + ".jpg";
    String path = CorePreferences.getAppTmpSDPath();
    File dir = new File(path);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    return new File(path + filename);
  }
}
