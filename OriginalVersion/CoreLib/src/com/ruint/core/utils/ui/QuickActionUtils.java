/*
 * QuickActionUtils.java
 * classes : com.ruint.core.utils.ui.QuickActionUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-17 上午10:52:56
 */
package com.ruint.core.utils.ui;

import java.util.List;

import com.ruint.core.view.quickaction.ActionItem;
import com.ruint.core.view.quickaction.QuickAction;

import android.content.Context;
import android.view.View;

/**
 * com.ruint.core.utils.ui.QuickActionUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-17 上午10:52:56
 */
public class QuickActionUtils {

  /**
   * @param anchor
   *          A view that trigger this QuickAction
   * @param context
   * @param actionItems
   * @param orientation
   *          {@value QuickAction#HORIZONTAL} {@value QuickAction#VERTICAL}
   * @param listener
   */
  public static void showQuickAction(Context context, List<ActionItem> actions, int orientation, View anchor,
      QuickAction.OnActionItemClickListener listener) {
    QuickAction quickAction = new QuickAction(context, orientation);
    mountActions(quickAction, actions);
    quickAction.setOnActionItemClickListener(listener);
    quickAction.show(anchor);
  }

  static void mountActions(QuickAction quickAction, List<ActionItem> actions) {
    for (ActionItem action : actions) {
      quickAction.addActionItem(action);
    }
  }
}
