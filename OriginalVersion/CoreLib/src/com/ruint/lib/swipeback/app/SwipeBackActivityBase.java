package com.ruint.lib.swipeback.app;

import com.ruint.lib.swipeback.SwipeBackLayout;

public interface SwipeBackActivityBase {
  /**
   * @return the SwipeBackLayout associated with this activity.
   */
  public abstract SwipeBackLayout getSwipeBackLayout();

  public abstract void setSwipeBackEnable(boolean enable);

  /**
   * Scroll out contentView and finish the activity
   */
  public abstract void scrollToFinishActivity();

}
