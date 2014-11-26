/*
 * BaseDiskCache.java
 * classes : com.ruint.core.cache.disk.BaseDiskCache
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:44:02
 */
package com.ruint.core.cache.disk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;
import android.util.Log;

import com.ruint.core.utils.FileUtils;
import com.ruint.core.utils.preferences.CorePreferences;

/**
 * com.ruint.core.cache.disk.BaseDiskCache
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:44:02
 */
public class BaseDiskCache implements DiskCache {
  private static final String TAG = "BaseDiskCache";
  private static final String NOMEDIA = ".nomedia";
  private static final int MIN_FILE_SIZE_IN_BYTES = 100;

  private File mStorageDirectory;

  private boolean available = false;

  public BaseDiskCache(String dirPath, String name) {
    // Lets make sure we can actually cache things!
    File baseDirectory;
    if (FileUtils.isSDCARDMounted()) {
      baseDirectory = new File(Environment.getExternalStorageDirectory(), dirPath);
    } else {
      baseDirectory = new File(dirPath);
    }

    File storageDirectory = new File(baseDirectory, name);
    available = createDirectory(storageDirectory);
    mStorageDirectory = storageDirectory;
    cleanupSimple();
  }

  @Override
  public boolean exists(String key) {
    return getFile(key).exists();
  }

  /**
   * This is silly, but our content provider *has* to serve content: URIs as
   * File/FileDescriptors using ContentProvider.openAssetFile, this is a
   * limitation of the StreamLoader that is used by the WebView. So, we handle
   * this by writing the file to disk, and returning a File pointer to it.
   * 
   * @param guid
   * @return
   */
  public File getFile(String hash) {
    return new File(mStorageDirectory.toString() + File.separator + Math.abs(hash.hashCode()));
  }

  public InputStream getInputStream(String hash) throws IOException {
    return (InputStream) new FileInputStream(getFile(hash));
  }

  public void store(String key, InputStream is) {
    CorePreferences.DEBUG(TAG + ": " + key);
    is = new BufferedInputStream(is);
    try {
      OutputStream os = new BufferedOutputStream(new FileOutputStream(getFile(key)));

      byte[] b = new byte[2048];
      int count;
      while ((count = is.read(b)) > 0) {
        os.write(b, 0, count);
      }
      os.close();
      CorePreferences.DEBUG(TAG + "store complete: " + key);
    } catch (IOException e) {
      CorePreferences.ERROR(TAG + "store failed to store: " + key, e);
      return;
    }
  }

  public void invalidate(String key) {
    getFile(key).delete();
  }

  public void cleanup() {
    // removes files that are too small to be valid. Cheap and cheater way to
    // remove files that
    // were corrupted during download.
    String[] children = mStorageDirectory.list();
    if (children != null) { // children will be null if hte directyr does not
                            // exist.
      for (int i = 0; i < children.length; i++) {
        File child = new File(mStorageDirectory, children[i]);
        if (!child.equals(new File(mStorageDirectory, NOMEDIA)) && child.length() <= MIN_FILE_SIZE_IN_BYTES) {
          CorePreferences.DEBUG(TAG + "Deleting: " + child);
          child.delete();
        }
      }
    }
  }

  /**
   * Temporary fix until we rework this disk cache. We delete the first 50
   * youngest files if we find the cache has more than 1000 images in it.
   */
  public void cleanupSimple() {
    final int maxNumFiles = 2000;
    final int numFilesToDelete = 100;

    String[] children = mStorageDirectory.list();
    if (children != null) {
      CorePreferences.DEBUG(TAG + "Found disk cache length to be: " + children.length);
      if (children.length > maxNumFiles) {
        CorePreferences.DEBUG(TAG + "Disk cache found to : " + children);
        for (int i = 0; i < numFilesToDelete; i++) {
          File child = new File(mStorageDirectory, children[i]);
          CorePreferences.DEBUG(TAG + "  deleting: " + child.getName());
          child.delete();
        }
      }
    }
  }

  public void clear() {
    // Clear the whole cache. Coolness.
    String[] children = mStorageDirectory.list();
    if (children != null) { // children will be null if hte directyr does not
                            // exist.
      for (int i = 0; i < children.length; i++) {
        File child = new File(mStorageDirectory, children[i]);
        if (!child.equals(new File(mStorageDirectory, NOMEDIA))) {
          CorePreferences.DEBUG(TAG + "Deleting: " + child);
          child.delete();
        }
      }
    }
    mStorageDirectory.delete();
  }

  private static final boolean createDirectory(File storageDirectory) {
    if (!storageDirectory.exists()) {
      Log.d(TAG, "Trying to create storageDirectory: " + String.valueOf(storageDirectory.mkdirs()));
      Log.d(TAG, "Exists: " + storageDirectory + " " + String.valueOf(storageDirectory.exists()));
      Log.d(TAG, "State: " + Environment.getExternalStorageState());
      Log.d(TAG, "Isdir: " + storageDirectory + " " + String.valueOf(storageDirectory.isDirectory()));
      Log.d(TAG, "Readable: " + storageDirectory + " " + String.valueOf(storageDirectory.canRead()));
      Log.d(TAG, "Writable: " + storageDirectory + " " + String.valueOf(storageDirectory.canWrite()));
      File tmp = storageDirectory.getParentFile();
      Log.d(TAG, "Exists: " + tmp + " " + String.valueOf(tmp.exists()));
      Log.d(TAG, "Isdir: " + tmp + " " + String.valueOf(tmp.isDirectory()));
      Log.d(TAG, "Readable: " + tmp + " " + String.valueOf(tmp.canRead()));
      Log.d(TAG, "Writable: " + tmp + " " + String.valueOf(tmp.canWrite()));
      tmp = tmp.getParentFile();
      Log.d(TAG, "Exists: " + tmp + " " + String.valueOf(tmp.exists()));
      Log.d(TAG, "Isdir: " + tmp + " " + String.valueOf(tmp.isDirectory()));
      Log.d(TAG, "Readable: " + tmp + " " + String.valueOf(tmp.canRead()));
      Log.d(TAG, "Writable: " + tmp + " " + String.valueOf(tmp.canWrite()));
    }

    File nomediaFile = new File(storageDirectory, NOMEDIA);
    if (!nomediaFile.exists()) {
      try {
        Log.d(TAG, "Created file: " + nomediaFile + " " + String.valueOf(nomediaFile.createNewFile()));
      } catch (IOException e) {
        e.printStackTrace();
        Log.d(TAG, "Unable to create .nomedia file for some reason.", e);
      }
    }

    // After we best-effort try to create the file-structure we need,
    // lets make sure it worked.
    if (!(storageDirectory.isDirectory() && storageDirectory.exists() && nomediaFile.exists())) {
      Log.d(TAG, "Unable to create storage directory and nomedia file.");
      return false;
    }
    return true;
  }

  @Override
  public boolean available() {
    return available;
  }
}
