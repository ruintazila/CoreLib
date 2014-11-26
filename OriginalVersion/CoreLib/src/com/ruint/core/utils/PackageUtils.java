/*
 * PackageUtil.java
 * classes : ruint.core.utils.PackageUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:03:13
 */
package com.ruint.core.utils;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

/**
 * ruint.core.utils.PackageUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:03:13
 */
public class PackageUtils {
  public static boolean isExistPackage(Context context, String packagename) {
    PackageManager manager = context.getPackageManager();
    List<PackageInfo> pkgList = manager.getInstalledPackages(0);
    for (int i = 0; i < pkgList.size(); i++) {
      PackageInfo pI = pkgList.get(i);
      if (pI.versionName == null) {
        continue;
      }
      if (pI.packageName.equalsIgnoreCase(packagename)) {
        return true;
      }
    }
    return false;
  }

  /***
   * @param context
   * @param archiveFilePath
   * @return
   */
  public static PackageInfo getApkInfo(Context context, String archiveFilePath) {
    PackageManager pm = context.getPackageManager();
    PackageInfo apkInfo = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_META_DATA);
    return apkInfo;
  }

  /***
   * @param context
   * @return
   */
  public static Bundle getAppMetaData(Context context) {
    ApplicationInfo appInfo = null;
    try {
      appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
      return appInfo.metaData;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ActivityInfo getActivityInfo(Activity activity) {
    try {
      PackageManager pm = activity.getPackageManager();
      ComponentName componentName = activity.getComponentName();
      return pm.getActivityInfo(componentName, 0);
    } catch (NameNotFoundException e) {
      return null;
    }
  }

  public static String getVersion(Context context) {
    PackageInfo info;
    try {
      info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      return info.versionName;
    } catch (NameNotFoundException e) {
      return "";
    }
  }
}
