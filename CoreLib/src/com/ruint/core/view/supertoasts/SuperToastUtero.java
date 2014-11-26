/*
 * SuperToastUtero.java
 * classes : com.ruint.core.view.supertoasts.SuperToastUtero
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-24 上午10:55:20
 */
package com.ruint.core.view.supertoasts;

import com.ruint.core.view.supertoasts.SuperToast.Animations;
import com.ruint.core.view.supertoasts.SuperToast.IconPosition;
import com.ruint.core.view.supertoasts.util.OnClickWrapper;
import com.ruint.core.view.supertoasts.util.OnDismissWrapper;

/**
 * com.ruint.core.view.supertoasts.SuperToastUtero
 * 
 * @author ruint <br/>
 *         create at 2014-11-24 上午10:55:20
 */
public abstract class SuperToastUtero {

  protected abstract SuperToastUtero setText(CharSequence text);

  protected abstract SuperToastUtero setAnimations(Animations animations);

  protected abstract SuperToastUtero setTextColor(int color);

  protected abstract SuperToastUtero setTextSize(int textSize);

  protected abstract SuperToastUtero setOnClickWrapper(OnClickWrapper clickWrapper);

  protected abstract SuperToastUtero setBackground(int bgColor);

  protected abstract SuperToastUtero setIcon(int imgRes, IconPosition iconPosition);

  protected abstract SuperToastUtero setDuration(int duration);

  protected abstract SuperToastUtero setOnDismissWrapper(OnDismissWrapper dismissWrapper);

}
