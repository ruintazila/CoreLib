/*
 * NullDiskCache.java
 * classes : com.ruint.core.cache.disk.NullDiskCache
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:46:15
 */
package com.ruint.core.cache.disk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * com.ruint.core.cache.disk.NullDiskCache
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:46:15
 */
public class NullDiskCache implements DiskCache {

  @Override
  public boolean exists(String key) {
    return false;
  }

  @Override
  public File getFile(String key) {
    return null;
  }

  @Override
  public InputStream getInputStream(String key) throws IOException {
    throw new FileNotFoundException();
  }

  @Override
  public void store(String key, InputStream is) {
  }

  @Override
  public void cleanup() {
  }

  @Override
  public void invalidate(String key) {
  }

  @Override
  public void clear() {
  }

  @Override
  public boolean available() {
    return false;
  }
}
