/*
 * DeviceUtil.java
 * classes : com.ruint.core.utils.DeviceUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:25:09
 */
package com.ruint.core.utils.devices;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * com.ruint.core.utils.DeviceUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:25:09
 */
public class DeviceUtils {
  public static boolean isNetConnect(Context context) {
    try {
      ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
          if (info.getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean isWifiConnect(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
      return true;
    }
    return false;
  }

  public static boolean isOpenLoaction(Context context) {
    try {
      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      boolean GPS_status = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
      boolean NETWORK_status = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
      if (GPS_status == true || NETWORK_status == true) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static String getDeviceId(Context context) {
    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager.getDeviceId();
  }
}
