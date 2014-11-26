/*
 * BackgroundSelectorUtil.java
 * classes : com.ruint.core.utils.ui.BackgroundSelectorUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午5:24:33
 */
package com.ruint.core.utils.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.CheckBox;

/**
 * com.ruint.core.utils.ui.BackgroundSelectorUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午5:24:33
 */
public class BackgroundSelectorUtils {
  public static StateListDrawable getPressedSelector(Context context, int idPressed, int idNormal) {
    StateListDrawable bg = new StateListDrawable();
    Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
    Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
    bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_pressed }, pressed);
    bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_focused }, pressed);
    bg.addState(new int[] {}, normal);
    return bg;
  }

  public static StateListDrawable getSelectedSelector(Context context, int idSelected, int idNormal) {
    StateListDrawable bg = new StateListDrawable();
    Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
    Drawable selected = idSelected == -1 ? null : context.getResources().getDrawable(idSelected);
    bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_selected }, selected);
    bg.addState(new int[] {}, normal);
    return bg;
  }

  public static StateListDrawable getCheckeddSelector(Context context, int idChecked, int idNormal) {
    StateListDrawable bg = new StateListDrawable();
    Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
    Drawable selected = idChecked == -1 ? null : context.getResources().getDrawable(idChecked);
    bg.addState(new int[] { android.R.attr.state_checked }, selected);
    bg.addState(new int[] {}, normal);
    return bg;
  }

  public static SpannableString getContentColor(Context context, String quoto, String message) {
    SpannableString sp = new SpannableString(quoto + message);
    sp.setSpan(new ForegroundColorSpan(context.getResources().getColor(android.R.color.transparent)), 0,
        quoto.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    sp.setSpan(new ForegroundColorSpan(context.getResources().getColor(android.R.color.transparent)), quoto.length(),
        sp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return sp;
  }

  public static SpannableString getContentColor(Context context, int quotoId, int messageId) {
    return getContentColor(context, context.getString(quotoId), context.getString(messageId));
  }

  public static SpannableString getContentColor(Context context, int quotoId, String message) {
    return getContentColor(context, context.getString(quotoId), message);
  }

  public static void setRightCheckBoxDrawable(Context context, int drawableId, CheckBox box) {
    Drawable drawable = context.getResources().getDrawable(drawableId); // /
    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    box.setCompoundDrawables(null, null, drawable, null);
  }
}
