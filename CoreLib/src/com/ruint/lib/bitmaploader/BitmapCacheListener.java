package com.ruint.lib.bitmaploader;

/**
 * com.ruint.lib.bitmapLoader
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:10:50
 */
public interface BitmapCacheListener {
  void onInitMemoryCacheFinished();

  void onInitDiskFinished();

  void onClearCacheFinished();

  void onClearMemoryCacheFinished();

  void onClearDiskCacheFinished();

  void onClearCacheFinished(String uri);

  void onClearMemoryCacheFinished(String uri);

  void onClearDiskCacheFinished(String uri);

  void onFlushCacheFinished();

  void onCloseCacheFinished();
}
