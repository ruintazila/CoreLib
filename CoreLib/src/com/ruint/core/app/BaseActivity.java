/*
 * BaseActivity.java
 * classes : com.ruint.core.app.BaseActivity
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-25 下午4:50:53
 */
package com.ruint.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ruint.core.bean.AnimBean;
import com.ruint.core.utils.volley.RequestManager;
import com.ruint.corelib.R;
import com.ruint.lib.swipeback.app.SwipeBackActivity;

/**
 * com.ruint.core.app.BaseActivity
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 下午4:50:53
 */
public abstract class BaseActivity extends SwipeBackActivity {
  protected Activity thisInstance;
  protected RequestManager mRequestManager;
  private int swipeBackOrientation;
  private boolean SwipeBack;
  protected static final boolean SWIPE_BACK_ENABLE = true;
  protected static final boolean SWIPE_BACK_DISABLE = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    thisInstance = this;
    mRequestManager = RequestManager.getInstance();
    pre4Create(savedInstanceState);
    initSwipeBack();
    findViews();
    handleRelevancy();
    initData();
  }

  protected abstract void pre4Create(Bundle savedInstanceState);

  protected abstract void handleRelevancy();

  protected abstract void findViews();

  protected abstract void initData();

  /**
   * In the method,u must first call @
   * {@link BaseActivity#setSwipeBack(boolean)} {@value #SWIPE_BACK_DISABLE}
   * {@value #SWIPE_BACK_ENABLE}if {@value #SWIPE_BACK_ENABLE}, call
   * {@link #setSwipeBackOrientation(int)}
   * 
   * @param savedInstanceState
   */
  private void initSwipeBack() {
    if (SWIPE_BACK_DISABLE) {
      setSwipeBackEnable(isSwipeBack());
    } else {
      getSwipeBackLayout().setEdgeTrackingEnabled(getSwipeBackOrientation());
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mRequestManager.cancelAll(this);  
  }

  @SuppressLint("NewApi")
  @Override
  public void startActivityForResult(Intent intent, int requestCode) {
    super.startActivityForResult(intent, requestCode);
    AnimBean animBean = getStartAnim();
    if (animBean != null && animBean.getIn() > 0 && animBean.getOut() > 0) {
      overridePendingTransition(animBean.getIn(), animBean.getOut());
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  @SuppressLint("NewApi")
  @Override
  public void startActivity(Intent intent) {
    super.startActivity(intent);
    AnimBean animBean = getStartAnim();
    if (animBean != null && animBean.getIn() > 0 && animBean.getOut() > 0) {
      overridePendingTransition(animBean.getIn(), animBean.getOut());
    }
  }

  @SuppressLint("NewApi")
  @Override
  public void finish() {
    super.finish();
    AnimBean animBean = getFinishAnim();
    if (animBean != null && animBean.getIn() > 0 && animBean.getOut() > 0) {
      overridePendingTransition(animBean.getIn(), animBean.getOut());
    }
  }

  protected String getTAG() {
    return this.getClass().getName();
  }

  public AnimBean getStartAnim() {
    return new AnimBean(R.anim.slide_in_right, R.anim.slide_fix);
  }

  public AnimBean getFinishAnim() {
    return new AnimBean(R.anim.slide_fix, R.anim.slide_out_right);
  }

  /**
   * @return the swipeBackOrientation
   */
  public int getSwipeBackOrientation() {
    return this.swipeBackOrientation;
  }

  /**
   * @param swipeBackOrientation
   *          the swipeBackOrientation to set
   */
  public void setSwipeBackOrientation(int swipeBackOrientation) {
    this.swipeBackOrientation = swipeBackOrientation;
  }

  /**
   * @return the swipeBack
   */
  public boolean isSwipeBack() {
    return this.SwipeBack;
  }

  /**
   * @param swipeBack
   *          the swipeBack to set
   */
  public void setSwipeBack(boolean swipeBack) {
    SwipeBack = swipeBack;
  }

}
