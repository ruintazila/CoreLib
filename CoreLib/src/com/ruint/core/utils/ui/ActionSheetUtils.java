/*
 * ActionSheetUtils.java
 * classes : com.ruint.core.utils.ui.ActionSheetUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-17 上午10:13:37
 */
package com.ruint.core.utils.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.ruint.core.view.ActionSheet;

/**
 * com.ruint.core.utils.ui.ActionSheetUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-17 上午10:13:37
 */
public class ActionSheetUtils {
  /**
   * 
   * @param context
   * @param fragmentManager
   * @param items
   * @param cancel
   *          String Use by
   *          {@link ActionSheet.Builder#setCancelButtonTitle(String)}
   * @param cancelable
   *          Use by
   *          {@link ActionSheet.Builder#setCancelableOnTouchOutside(boolean)}
   * @param listener
   */
  public static void showActionSheet(Context context, FragmentManager fragmentManager, String[] items, String cancel,
      boolean cancelable, ActionSheet.ActionSheetListener listener) {
    ActionSheet.createBuilder(context, fragmentManager).setCancelButtonTitle(cancel).setOtherButtonTitles(items)
        .setCancelableOnTouchOutside(cancelable).setListener(listener).show();
  }

  /**
   * invoke by
   * {@link #showActionSheet(Context, FragmentManager, String[], String, ActionSheet.ActionSheetListener)}
   * 
   * @param builder
   * @param count
   *          range from 1-4
   */
  @Deprecated
  static void itemEnumerator(ActionSheet.Builder builder, int count, String[] items) {
    switch (count) {
      case 1:
        break;
      case 2:

        break;
      case 3:

        break;
      case 4:

        break;
      default:
        break;
    }
  }
}
