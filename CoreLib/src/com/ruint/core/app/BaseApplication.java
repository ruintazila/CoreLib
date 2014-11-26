/*
 * BaseApplication.java
 * classes : ruint.core.app.BaseApplication
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午10:40:36
 */
package com.ruint.core.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.location.Location;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.ruint.core.cache.db.CacheDBService;
import com.ruint.core.cache.disk.BaseDiskCache;
import com.ruint.core.cache.disk.DiskCache;
import com.ruint.core.cache.disk.NullDiskCache;
import com.ruint.core.exception.CrashHandler;
import com.ruint.core.utils.FileUtils;
import com.ruint.core.utils.PackageUtils;
import com.ruint.core.utils.devices.DeviceUtils;
import com.ruint.core.utils.preferences.CorePreferences;
import com.ruint.core.utils.preferences.PreferencesUtils;
import com.ruint.lib.imageloader.cache.disc.naming.Md5FileNameGenerator;
import com.ruint.lib.imageloader.core.ImageLoader;
import com.ruint.lib.imageloader.core.ImageLoaderConfiguration;
import com.ruint.lib.imageloader.core.assist.QueueProcessingType;

/**
 * ruint.core.app.BaseApplication
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午10:40:36
 */
public abstract class BaseApplication extends Application {

  protected PreferencesUtils prefUtil;
  private SharedPreferences mPrefs;
  protected DisplayMetrics metrics;
  protected float density;
  protected DiskCache diskcache;
  protected Location location;
  private int screenWidth;
  private int screenHeight;
  private String deviceId;
  private String version;
  public CorePreferences AppCore;
  private final String KEY_ENABLE_IMG = "KEY_ENABLE_IMG";
  private final String KEY_FIRST_USE = "KEY_FIRST_USE";

  public void onCreate() {
    super.onCreate();
    AppCore = CorePreferences.getInstance(this);

    CrashHandler crashHandler = CrashHandler.getInstance();
    crashHandler.init(getApplicationContext());

    prefUtil = new PreferencesUtils(this);
    mPrefs = prefUtil.getSharedPreferences();
    if (FileUtils.isSDCARDMounted()) {
      diskcache = new BaseDiskCache(AppCore.getCoreConfig().getAppTag(), CorePreferences.CACHEPATH);
    } else {
      diskcache = new BaseDiskCache(getCacheDir().getPath(), CorePreferences.CACHEPATH);
    }
    if (!diskcache.available()) {
      diskcache = new NullDiskCache();
    }

    setDeviceId(DeviceUtils.getDeviceId(this));
    setVersion(PackageUtils.getVersion(this));
    initWindow();

    // Init baidu map sdk
    SDKInitializer.initialize(getApplicationContext());

    onAppCreate();
  }

  public CacheDBService getCacheService() {
    return CacheDBService.getInstance(this);
  }

  protected abstract void onAppCreate();

  protected abstract void onAppDestory();

  public abstract void onAppCancel();

  protected void initImageLoader(Context context) {
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
        .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
        .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
        .writeDebugLogs().build();
    ImageLoader.getInstance().init(config);
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @SuppressLint("NewApi")
  public void initWindow() {
    WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    Point outSize = new Point();
    wm.getDefaultDisplay().getSize(outSize);
    screenWidth = outSize.x;
    screenHeight = outSize.y;
    DisplayMetrics dm = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(dm);
    setMetrics(dm);
    setDensity(dm.density);
  }

  public PreferencesUtils getPrefsUtil() {
    return prefUtil;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public DiskCache getDiskCache() {
    return diskcache;
  }

  public SharedPreferences getSharedPreferences() {
    return mPrefs;
  }

  public void setDensity(float density) {
    this.density = density;
  }

  public float getDensity() {
    return density;
  }

  public DisplayMetrics getMetrics() {
    return metrics;
  }

  public void setMetrics(DisplayMetrics metrics) {
    this.metrics = metrics;
  }

  public void cleanAllFile() {
    try {
      FileUtils.delAllFile(CorePreferences.getAppTmpSDPath());
      FileUtils.delAllFile(CorePreferences.getAppDownloadSDPath());
      FileUtils.delAllFile(CorePreferences.getAppImageSDPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** 是否启动图片模式 **/
  public void enableImg(boolean enable) {
    prefUtil.putBoolean(KEY_ENABLE_IMG, enable);
  }

  public boolean isEnableImg() {
    return prefUtil.getBoolean(KEY_ENABLE_IMG, true);
  }

  /* 是否第一次使用该客户端* */
  public void setIsFirst(boolean first) {
    prefUtil.putBoolean(KEY_FIRST_USE, first);
  }

  public boolean isFirst() {
    return prefUtil.getBoolean(KEY_FIRST_USE, true);
  }

  /* 該版本是否第一次使用* */
  public void setIsFirstVersion(boolean first) {
    String versionName = PackageUtils.getVersion(this);
    prefUtil.putBoolean(KEY_FIRST_USE + versionName, first);
  }

  public boolean isFirstVersion() {
    String versionName = PackageUtils.getVersion(this);
    return prefUtil.getBoolean(KEY_FIRST_USE + versionName, true);
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    onAppDestory();
  }

  /**
   * 子类中实现该方法，获取当前城市的中文名，比如南京
   * 
   * @return
   */
  public abstract String getCityName();

}
