/*
 * BasePhotoCropActivityWithoutSwipe.java
 * classes : com.ruint.lib.crop.BasePhotoCropActivityWithoutSwipe
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-25 下午4:57:00
 */
package com.ruint.lib.crop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.ruint.core.app.BaseActivity;

/**
 * com.ruint.lib.crop.BasePhotoCropActivityWithoutSwipe
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 下午4:57:00
 */
public abstract class BasePhotoCropActivity extends BaseActivity implements CropHandler {

  protected CropParams mCropParams = new CropParams();

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    CropHelper.handleResult(this, requestCode, resultCode, data);
  }

  @Override
  public void onPhotoCropped(Uri uri) {
  }

  @Override
  public void onCropCancel() {
  }

  @Override
  public void onCropFailed(String message) {
  }

  @Override
  public CropParams getCropParams() {
    return mCropParams;
  }

  @Override
  public Activity getContext() {
    return this;
  }

  @Override
  protected void onDestroy() {
    if (getCropParams() != null)
      CropHelper.clearCachedCropFile(getCropParams().uri);
    super.onDestroy();
  }
}
