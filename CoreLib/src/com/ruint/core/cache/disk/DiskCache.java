/*
 * DiskCache.java
 * classes : com.ruint.core.cache.disk.DiskCache
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:42:34
 */
package com.ruint.core.cache.disk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * com.ruint.core.cache.disk.DiskCache
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:42:34
 */
public interface DiskCache {

  public boolean exists(String key);

  public File getFile(String key);

  public InputStream getInputStream(String key) throws IOException;

  public void store(String key, InputStream is);

  public void invalidate(String key);

  public void cleanup();

  public void clear();

  public boolean available();
}
