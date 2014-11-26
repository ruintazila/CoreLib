/*
 * CorePreferences.java
 * classes : ruint.core.utils.CorePreferences
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:04:35
 */
package com.ruint.core.utils.preferences;

import java.io.File;

import com.ruint.core.bean.CoreConfig;
import com.ruint.core.utils.PackageUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

/**
 * ruint.core.utils.CorePreferences
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:04:35
 */
public class CorePreferences {
  private static CorePreferences CorePreferences = null;
  private static CoreConfig coreConfig;
  public static final String CACHEPATH = "cache";
  public static final String IMAGEPATH = "image";
  public static final String TEMPPATH = "temp";
  public static final String DOWNLOADPATH = "download";
  public static final int avgpage = 20;

  public CorePreferences(Context context) {
    coreConfig = initConfig(context);
  }

  public static CorePreferences getInstance(Context context) {
    if (CorePreferences == null) {
      return new CorePreferences(context);
    }
    return CorePreferences;
  }

  public CoreConfig getCoreConfig() {
    return coreConfig;
  }

  public CoreConfig initConfig(Context context) {
    if (coreConfig == null) {
      Bundle metaBundle = PackageUtils.getAppMetaData(context);
      if (metaBundle != null) {
        coreConfig = new CoreConfig();
        coreConfig.setAppName(metaBundle.getString("app_name"));
        coreConfig.setAppTag(metaBundle.getString("app_tag"));
        coreConfig.setOpenBaiduStat(metaBundle.getBoolean("app_baidustat", false));

        coreConfig.setAppBaiduMapKey(metaBundle.getString("app_baidumapkey"));
        coreConfig.setDebug(metaBundle.getBoolean("app_isdebug", true));
        coreConfig.setAnalyse(metaBundle.getBoolean("app_isanalyse", false));
        if (coreConfig.isAnalyse()) {
          coreConfig.setAnalyseUrl(metaBundle.getString("app_analyse_url"));
          coreConfig.setAnalyseBufferSize(metaBundle.getInt("app_analyse_buffersize", 10));
          coreConfig.setAnalyseChannel(String.valueOf(metaBundle.get("app_analyse_channel")));
        }
        coreConfig.setDefaultCity(metaBundle.getString("app_default_city"));
        coreConfig.setDefaultCityForce(metaBundle.getBoolean("app_default_city_force", false));
      }
    }
    return coreConfig;
  }

  public static final String getAppSDPath() {
    File file = new File(Environment.getExternalStorageDirectory(), coreConfig.getAppTag());
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final String getAppCacheSDPath() {
    File file = new File(getAppSDPath(), CACHEPATH);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final String getAppTmpSDPath() {
    File file = new File(getAppSDPath(), TEMPPATH);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final String getAppDownloadSDPath() {
    File file = new File(getAppSDPath(), DOWNLOADPATH);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final String getAppImageSDPath() {
    File file = new File(getAppSDPath(), IMAGEPATH);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final String getAppApkFile() {
    return getAppSDPath() + "/" + coreConfig.getAppTag() + "_update.apk";
  }

  /**
   * 获取app自定义子目录路径
   * 
   * @param subDir
   * @return
   */
  public static final String getAppSDPathWithSubDir(String subDir) {
    File file = new File(getAppSDPath(), subDir);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }

  public static final void DEBUG(String s) {
    if (coreConfig.isDebug()) {
      Log.i(coreConfig.getAppTag(), s);
    }
  }

  public static final void ERROR(Throwable e) {
    if (coreConfig.isDebug()) {
      Log.e(coreConfig.getAppTag(), e.getMessage(), e);
    }
  }

  public static final void ERROR(String s) {
    if (coreConfig.isDebug()) {
      Log.e(coreConfig.getAppTag(), s);
    }
  }

  public static final void ERROR(String s, Throwable e) {
    if (coreConfig.isDebug()) {
      Log.e(coreConfig.getAppTag(), s, e);
    }
  }

  public static final String getAppTAG() {
    return coreConfig.isDebug() ? coreConfig.getAppTag() : "DEBUG";
  }
}
