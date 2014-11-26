/*
 * NotifyUtils.java
 * classes : com.ruint.core.utils.ui.NotifyUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-14 下午2:43:45
 */
package com.ruint.core.utils.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.ruint.lib.niftynotification.Configuration;
import com.ruint.lib.niftynotification.Effects;
import com.ruint.lib.niftynotification.NiftyNotificationView;

/**
 * com.ruint.core.utils.ui.NotifyUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-14 下午2:43:45
 */
public class NotifyUtils {

  // You can configure like this
  // The default

  /*
   * Configuration cfg=new Configuration.Builder() .setAnimDuration(700)
   * .setDispalyDuration(1500) .setBackgroundColor("#FFBDC3C7")
   * .setTextColor("#FF444444") .setIconBackgroundColor("#FFFFFFFF")
   * .setTextPadding(5) //dp .setViewHeight(48) //dp .setTextLines(2) //You had
   * better use setViewHeight and setTextLines together
   * .setTextGravity(Gravity.CENTER) //only text def Gravity.CENTER,contain icon
   * Gravity.CENTER_VERTICAL .build();
   * 
   * NiftyNotificationView.build(this,msg, effect,R.id.mLyout,cfg)
   * .setIcon(R.drawable.lion) //remove this line ,only text
   * .setOnClickListener(new View.OnClickListener() {
   * 
   * @Override public void onClick(View view) { //add your code } }) .show(); //
   * show(boolean) allow duplicates or showSticky() sticky notification,you can
   * call removeSticky() method close it
   */

  /**
   * 
   * @param activity
   * @param text
   * @param effects
   *          {@link Effects#thumbSlider}{@link Effects#standard}
   *          {@link Effects#flip}{@link Effects#jelly}{@link Effects#scale}
   *          {@link Effects#slideIn}{@link Effects#slideOnTop}
   * @param viewGroupResId
   *          a FullScreenLayout that with
   *          {@link ViewGroup.Layoutparam.MATCH_PARENT} and {@link android
   *          :clipChildren}
   * @param configuration
   *          can be null(use default)
   * @param icon
   */
  public static void showNotifyEffect(Activity activity, String text, Drawable icon, Effects effects,
      int viewGroupResId, Configuration configuration, OnClickListener listener) {
    if (configuration != null) {
      NiftyNotificationView.build(activity, text, effects, viewGroupResId, configuration).setOnClickListener(listener)
          .setIcon(icon).show();
    } else {
      NiftyNotificationView.build(activity, text, effects, viewGroupResId).setOnClickListener(listener).setIcon(icon)
          .show();
    }
  }
}
