/*
 * ToastUtils.java
 * classes : com.ruint.core.utils.ui.ToastUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午5:27:20
 */
package com.ruint.core.utils.ui;

import android.app.Activity;
import android.content.Context;

import com.ruint.core.view.supertoasts.SuperActivityToast;
import com.ruint.core.view.supertoasts.SuperCardToast;
import com.ruint.core.view.supertoasts.SuperToast;
import com.ruint.core.view.supertoasts.SuperToast.Animations;
import com.ruint.core.view.supertoasts.util.OnClickWrapper;
import com.ruint.core.view.supertoasts.util.OnDismissWrapper;

/**
 * com.ruint.core.utils.ui.ToastUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午5:27:20
 */
public class ToastUtils {

  public static final int TOUCH_TO_DISMISS = 0x1;
  public static final int SWIPE_TO_DISMISS = 0x2;

  /**
   * 
   * @param context
   * @param msg
   * @param bgColor
   *          {@link SuperToast.Background} {@value
   *          BLACK,BLUE,GRAY,GREEN,ORANGE,PURPLE,RED,WHITE}
   * @param duration
   *          {@link SuperToast.Duration} {@value
   *          VERY_SHORT=(1500),SHORT=(2000),MEDIUM=(2750),LONG=(3500),
   *          EXTRA_LONG=(4500)}
   * @param textsize
   *          {@link SuperToast.TextSize} {@value EXTRA_SMALL = (12); SMALL =
   *          (14); MEDIUM = (16),LARGE = (18)}
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param imgRes
   *          Resource ID of a drawable.If u don't want this,call by value 0.
   * @param imgPosition
   *          {@link SuperToast.IconPosition}{@value LEFT,RIGHT,TOP,BOTTOM}. If
   *          positon is null,default value will be
   *          SuperToast.IconPosition.LEFT;
   */
  public static void showCustomToast(Context context, String msg, int bgColor, int duration, int textSize,
      SuperToast.Animations anim, int imgRes, SuperToast.IconPosition imgPosition) {
    SuperToast.create(context).setAnimations(anim).setDuration(duration)
        .setIcon(imgRes, imgPosition == null ? SuperToast.IconPosition.LEFT : imgPosition).setText(msg)
        .setBackground(bgColor).setTextSize(textSize).show();
  }

  /**
   * Create a Default SuperToast with Icon.
   * 
   * @param context
   * @param msg
   */
  public static void showCustomToast(Context context, String msg, int imgRes, SuperToast.IconPosition imgPosition) {
    SuperToast.create(context).setAnimations(SuperToast.Animations.FADE).setDuration(SuperToast.Duration.SHORT)
        .setText(msg).setTextSize(SuperToast.TextSize.SMALL)
        .setIcon(imgRes, imgPosition == null ? SuperToast.IconPosition.LEFT : imgPosition).show();
  }

  /**
   * Create a Default SuperToast without Icon.
   * 
   * @param bgColor
   * @param context
   * @param msg
   */
  public static void showCustomToast(Context context, String msg, int bgColor) {
    SuperToast.create(context).setText(msg).setBackground(bgColor).show();
  }

  /**
   * Create a Default SuperToast without Icon.
   * 
   * @param context
   * @param msg
   */
  public static void showCustomToast(Context context, String msg) {
    SuperToast.create(context).setText(msg).show();
  }

  /**
   * Create a Default SuperToast with Animation.
   * 
   * @param context
   * @param msg
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   */
  public static void showCustomToast(Context context, String msg, SuperToast.Animations anim) {
    SuperToast.create(context).setText(msg).setAnimations(anim).show();
  }

  /**
   * Create a Default SuperToast with Animation.
   * 
   * @param context
   * @param msg
   * @param bgColor
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   */
  public static void showCustomToast(Context context, String msg, int bgColor, SuperToast.Animations anim) {
    SuperToast.create(context).setAnimations(anim).setText(msg).setBackground(bgColor).show();
  }

  /**
   * Create a Default SuperToast with Animation and Icon.
   * 
   * @param context
   * @param msg
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param imgRes
   *          Resource ID of a drawable.If u don't want this,call by value 0.
   * @param imgPosition
   *          {@link SuperToast.IconPosition}{@value LEFT,RIGHT,TOP,BOTTOM}. If
   *          positon is null,default value will be
   *          SuperToast.IconPosition.LEFT;
   */
  public static void showCustomToast(Context context, String msg, SuperToast.Animations anim, int imgRes,
      SuperToast.IconPosition imgPosition) {
    SuperToast.create(context).setAnimations(anim).setText(msg)
        .setIcon(imgRes, imgPosition != null ? imgPosition : SuperToast.IconPosition.LEFT).show();
  }

  /**
   * Fully customized SuperActivityToast.Nobody will use this...I guess >.<
   * 
   * @param context
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param bgColor
   *          {@link SuperToast.Background} {@value
   *          BLACK,BLUE,GRAY,GREEN,ORANGE,PURPLE,RED,WHITE}
   * @param duration
   *          {@link SuperToast.Duration} {@value
   *          VERY_SHORT=(1500),SHORT=(2000),MEDIUM=(2750),LONG=(3500),
   *          EXTRA_LONG=(4500)}
   * @param textsize
   *          {@link SuperToast.TextSize} {@value EXTRA_SMALL = (12); SMALL =
   *          (14); MEDIUM = (16),LARGE = (18)}
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param imgRes
   *          Resource ID of a drawable.If u don't want this,call by value 0.
   * @param imgPosition
   *          {@link SuperToast.IconPosition}{@value LEFT,RIGHT,TOP,BOTTOM}. If
   *          positon is null,default value will be
   *          SuperToast.IconPosition.LEFT;
   * @param listener
   */
  public static void showActivityToast(Activity context, SuperToast.Type type, String msg, SuperToast.Animations anim,
      int duration, int textSize, int textColor, int bgColor, int imgRes, SuperToast.IconPosition imgPosition,
      OnClickWrapper clickWrapper, OnDismissWrapper dismissWrapper) {
    SuperActivityToast superToast = SuperActivityToast.create(context, type);
    handleIcon(imgRes, imgPosition, superToast);
    handleType(type, clickWrapper, superToast);
    superToast.setAnimations(anim).setDuration(duration).setText(msg).setTextSize(textSize).setTextColor(textColor)
        .setBackground(bgColor).setOnDismissWrapper(dismissWrapper).show();
  }

  private static void handleIcon(int imgRes, SuperToast.IconPosition imgPosition, SuperActivityToast superToast) {
    if (imgRes > 0) {
      superToast.setIcon(imgRes, imgPosition != null ? imgPosition : SuperToast.IconPosition.LEFT);
    }
  }

  /**
   * Create a Default SuperToast.
   * 
   * @param context
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param clickWrapper
   * @param dismissWrapper
   */
  public static void showActivityToast(Activity context, SuperToast.Type type, String msg, OnClickWrapper clickWrapper,
      OnDismissWrapper dismissWrapper) {
    SuperActivityToast superToast = SuperActivityToast.create(context, type);
    superToast.setText(msg);
    handleType(type, clickWrapper, superToast);
    superToast.setOnDismissWrapper(dismissWrapper);
    superToast.show();
  }

  /**
   * Create a Default SuperToast with animation.
   * 
   * @param context
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param clickWrapper
   * @param dismissWrapper
   */
  public static void showActivityToast(Activity context, SuperToast.Type type, String msg, SuperToast.Animations anim,
      OnClickWrapper clickWrapper, OnDismissWrapper dismissWrapper) {
    SuperActivityToast superToast = new SuperActivityToast(context, type);
    superToast.setText(msg);
    superToast.setAnimations(anim);
    handleType(type, clickWrapper, superToast);
    superToast.setOnDismissWrapper(dismissWrapper);
    superToast.show();
  }

  /**
   * Create a Default SuperToast with animation and icon.
   * 
   * @param context
   * @param type
   * @param msg
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param imgRes
   *          Resource ID of a drawable.If u don't want this,call by value 0.
   * @param imgPosition
   *          {@link SuperToast.IconPosition}{@value LEFT,RIGHT,TOP,BOTTOM}. If
   *          positon is null,default value will be
   *          SuperToast.IconPosition.LEFT;
   * @param clickWrapper
   * @param dismissWrapper
   */
  public static void showActivityToast(Activity context, SuperToast.Type type, String msg, SuperToast.Animations anim,
      int imgRes, SuperToast.IconPosition imgPosition, OnClickWrapper clickWrapper, OnDismissWrapper dismissWrapper) {
    SuperActivityToast superToast = new SuperActivityToast(context, type);
    superToast.setText(msg);
    superToast.setAnimations(anim);
    handleType(type, clickWrapper, superToast);
    handleIcon(imgRes, imgPosition, superToast);
    superToast.setOnDismissWrapper(dismissWrapper);
    superToast.show();
  }

  private static void handleType(SuperToast.Type type, OnClickWrapper clickWrapper, SuperActivityToast superToast) {
    if (type == SuperToast.Type.BUTTON) {
      superToast.setOnClickWrapper(clickWrapper);
    } else if (type == SuperToast.Type.PROGRESS_HORIZONTAL) {
      superToast.setIndeterminate(true);
    }
  }

  /**
   * Fully customized SuperActivityToast.Nobody will use this...I guess >.<
   * 
   * @param context
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param bgColor
   *          {@link SuperToast.Background} {@value
   *          BLACK,BLUE,GRAY,GREEN,ORANGE,PURPLE,RED,WHITE}
   * @param duration
   *          {@link SuperToast.Duration} {@value
   *          VERY_SHORT=(1500),SHORT=(2000),MEDIUM=(2750),LONG=(3500),
   *          EXTRA_LONG=(4500)}
   * @param textsize
   *          {@link SuperToast.TextSize} {@value EXTRA_SMALL = (12); SMALL =
   *          (14); MEDIUM = (16),LARGE = (18)}
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param imgRes
   *          Resource ID of a drawable.If u don't want this,call by value 0.
   * @param imgPosition
   *          {@link SuperToast.IconPosition}{@value LEFT,RIGHT,TOP,BOTTOM}. If
   *          positon is null,default value will be
   *          SuperToast.IconPosition.LEFT;
   * @param dismissMode
   *          {@value #TOUCH_TO_DISMISS}{@value #SWIPE_TO_DISMISS}
   * @param listener
   */
  public static void showCardToast(Activity context, SuperToast.Type type, String msg, SuperToast.Animations anim,
      int duration, int textSize, int textColor, int bgColor, int imgRes, SuperToast.IconPosition imgPosition,
      int dismissMode, OnClickWrapper clickWrapper, OnDismissWrapper dismissWrapper) {
    SuperCardToast superToast = new SuperCardToast(context, type);
    superToast.setAnimations(anim);
    superToast.setDuration(duration);
    superToast.setText(msg);
    superToast.setTextSize(textSize);
    superToast.setTextColor(textColor);
    superToast.setBackground(bgColor);
    handleDismissMode(dismissMode, superToast);
    handleIcon(imgRes, imgPosition, superToast);
    handleType(type, clickWrapper, superToast);
    superToast.setOnDismissWrapper(dismissWrapper);
    superToast.show();
  }

  /**
   * 
   * @param context
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param dismissMode
   *          {@value #TOUCH_TO_DISMISS}{@value #SWIPE_TO_DISMISS}
   * @param clickWrapper
   */
  public static void showCardToast(Activity context, SuperToast.Type type, String msg, int dismissMode,
      OnClickWrapper clickWrapper) {
    SuperCardToast superToast = new SuperCardToast(context, type);
    superToast.setText(msg);
    handleDismissMode(dismissMode, superToast);
    handleType(type, clickWrapper, superToast);
    superToast.show();
  }

  /**
   * 
   * @param context
   * @param type
   *          {@link SuperToast.Type}{@value STANDARD, PROGRESS,
   *          PROGRESS_HORIZONTAL, BUTTON}
   * @param msg
   * @param dismissMode
   *          {@value #TOUCH_TO_DISMISS}{@value #SWIPE_TO_DISMISS}
   * @param anim
   *          {@link SuperToast.Animations}{@value FADE, FLYIN, SCALE, POPUP}
   * @param clickWrapper
   */
  public static void showCardToast(Activity context, SuperToast.Type type, String msg, int dismissMode,
      Animations anim, OnClickWrapper clickWrapper) {
    SuperCardToast superToast = new SuperCardToast(context, type);
    superToast.setText(msg);
    superToast.setAnimations(anim);
    handleDismissMode(dismissMode, superToast);
    handleType(type, clickWrapper, superToast);
    superToast.show();
  }

  private static void handleDismissMode(int dismissMode, SuperCardToast superToast) {
    switch (dismissMode) {
      case TOUCH_TO_DISMISS:
        superToast.setTouchToDismiss(true);
        break;
      case SWIPE_TO_DISMISS:
        superToast.setSwipeToDismiss(true);
        break;
    }
  }

  private static void handleType(SuperToast.Type type, OnClickWrapper clickWrapper, SuperCardToast superToast) {
    if (type == SuperToast.Type.BUTTON) {
      superToast.setOnClickWrapper(clickWrapper);
    } else if (type == SuperToast.Type.PROGRESS_HORIZONTAL) {
      superToast.setIndeterminate(true);
    }
  }

  private static void handleIcon(int imgRes, SuperToast.IconPosition imgPosition, SuperCardToast superToast) {
    if (imgRes > 0) {
      superToast.setIcon(imgRes, imgPosition != null ? imgPosition : SuperToast.IconPosition.LEFT);
    }
  }

  // public static final int TOAST_TYPE_NORMAL = 0x111;
  // public static final int TOAST_TYPE_ACTIVITY = 0x222;
  // public static final int TOAST_TYPE_CARD = 0x333;

  // private SuperToast mSuperToast;
  // private SuperActivityToast mActivityToast;
  // private SuperCardToast mCardToast;

  class SuperToastBuilder<T> {
    private T t;
    @SuppressWarnings({ "rawtypes", "unused" })
    private Class clazz;

    public SuperToastBuilder<T> build(T t) {
      this.t = t;
      obtainClass();
      return this;
    }

    private void obtainClass() {
      clazz = t.getClass();
    }

    public SuperToastBuilder<T> setText(String text) {
      ((SuperToast) t).setText(text);
      return this;
    }
  }
}
