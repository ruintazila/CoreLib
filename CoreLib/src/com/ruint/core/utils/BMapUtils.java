/*
 * BMapUtil.java
 * classes : com.ruint.core.utils.BMapUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:46:38
 */
package com.ruint.core.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.ruint.core.utils.preferences.CorePreferences;

/**
 * com.ruint.core.utils.BMapUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:46:38
 */
public class BMapUtils {

  /**
   * 百度静态图API地址.
   */
  public static String STATIC_IMAGE_URL = "http://api.map.baidu.com/staticimage";

  /**
   * 移除百度地图的缩放控件.
   */
  public static void removeZoomControls(MapView view) {
    view.removeViewAt(2);
  }

  private static enum GeoPointScreen {
    TOP_LEFT, BOTTOM_RIGHT
  }

  /**
   * 获取静态地图的图片URL.
   * 
   * @param lat
   *          纬度
   * @param lng
   *          经度
   * @param zoom
   *          缩放级别
   * @param width
   *          图片宽度
   * @param height
   *          图片高度
   * @return 图片URL
   */
  public static String createStaticImageUrl(Context context, double lat, double lng, int zoom, int width, int height) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    if (dm.densityDpi > DisplayMetrics.DENSITY_HIGH) {
      CorePreferences.DEBUG("Using high dpi image.");
      width /= 2;
      height /= 2;
      String url = STATIC_IMAGE_URL + "?width=%d&height=%d&center=%f,%f&zoom=%d&markers=%f,%f&scale=2";
      return String.format(url, width, height, lng, lat, zoom, lng, lat);
    } else {
      CorePreferences.DEBUG("Using low dpi image.");
      String url = STATIC_IMAGE_URL + "?width=%d&height=%d&center=%f,%f&zoom=%d&markers=%f,%f";
      return String.format(url, width, height, lng, lat, zoom, lng, lat);
    }
  }

  /**
   * 判断经纬度是否正确
   * 
   * @param lat
   *          经度
   * @param lng
   *          纬度
   * @return 经纬度是否正确
   */
  public static boolean isLatLngValid(double lat, double lng) {
    if (lat == 0 || lng == 0) {
      return false;
    }
    if (lat < -90 || lat > 90) {
      return false;
    }
    if (lng < -180 || lat > 180) {
      return false;
    }
    return true;
  }

  /**
   * 判断经纬度是否正确.
   * 
   * @param ll
   *          经纬度信息
   * @return 经纬度是否有效
   */
  public static boolean isLatLngValid(LatLng ll) {
    return isLatLngValid(ll.latitude, ll.longitude);
  }

  /**
   * 获得MapView左上角的地理坐标.
   */
  public static LatLng getTopLeftGeoPoint(MapView mapView) {
    return getGeoPointOfScreen(mapView, GeoPointScreen.TOP_LEFT);
  }

  /**
   * 获得MapView右下角的地理坐标.
   */
  public static LatLng getBottomRightGeoPoint(MapView mapView) {
    return getGeoPointOfScreen(mapView, GeoPointScreen.BOTTOM_RIGHT);
  }

  /**
   * 获得屏幕坐标位置
   */
  private static LatLng getGeoPointOfScreen(MapView mapView, GeoPointScreen screenType) {
    Projection projection = mapView.getMap().getProjection();
    if (projection != null) {
      if (screenType == GeoPointScreen.TOP_LEFT) {
        return projection.fromScreenLocation(new Point(0, 0));
      } else if (screenType == GeoPointScreen.BOTTOM_RIGHT) {
        int w = 0, h = 0;
        if (mapView.getWidth() != 0) {
          w = mapView.getWidth();
          h = mapView.getHeight();
        }
        if (w == 0 && mapView.getMeasuredWidth() != 0) {
          w = mapView.getMeasuredWidth();
          h = mapView.getMeasuredHeight();
        }
        // if (w == 0) {
        // w = mApplication.getMetrics().widthPixels;
        // h = mApplication.getMetrics().heightPixels;
        // }
        return projection.fromScreenLocation(new Point(w, h));
      } else {
        return projection.fromScreenLocation(new Point(0, 0));
      }
    } else {
      return null;
    }
  }
}
