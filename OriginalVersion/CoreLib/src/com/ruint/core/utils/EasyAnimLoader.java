/*
 * EasyAnimLoader.java
 * classes : com.ruint.core.utils.anim.EasyAnimLoader
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-14 上午11:39:52
 */
package com.ruint.core.utils;

import java.util.ArrayList;

import android.graphics.Point;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.BlindAnimation;
import com.easyandroidanimations.library.BlinkAnimation;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FadeInAnimation;
import com.easyandroidanimations.library.FadeOutAnimation;
import com.easyandroidanimations.library.FlipHorizontalAnimation;
import com.easyandroidanimations.library.FlipHorizontalToAnimation;
import com.easyandroidanimations.library.FlipVerticalAnimation;
import com.easyandroidanimations.library.FlipVerticalToAnimation;
import com.easyandroidanimations.library.FoldAnimation;
import com.easyandroidanimations.library.FoldLayout.Orientation;
import com.easyandroidanimations.library.HighlightAnimation;
import com.easyandroidanimations.library.PathAnimation;
import com.easyandroidanimations.library.PuffInAnimation;
import com.easyandroidanimations.library.PuffOutAnimation;
import com.easyandroidanimations.library.RotationAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;
import com.easyandroidanimations.library.ScaleOutAnimation;
import com.easyandroidanimations.library.ShakeAnimation;
import com.easyandroidanimations.library.SlideInAnimation;
import com.easyandroidanimations.library.SlideInUnderneathAnimation;
import com.easyandroidanimations.library.SlideOutAnimation;
import com.easyandroidanimations.library.SlideOutUnderneathAnimation;
import com.easyandroidanimations.library.TransferAnimation;
import com.easyandroidanimations.library.UnfoldAnimation;

/**
 * com.ruint.core.utils.anim.EasyAnimLoader
 * 
 * @author ruint <br/>
 *         create at 2014-11-14 上午11:39:52
 */
public class EasyAnimLoader {

  public static void playBlind(View view) {
    new BlindAnimation(view).animate();
  }

  public static void playExplode(View view) {
    new ExplodeAnimation(view).animate();

  }

  public static void playFadeIn(View view) {
    new FadeInAnimation(view).animate();
  }

  public static void playFadeOut(View view) {
    new FadeOutAnimation(view).animate();
  }

  public static void playBlink(View view, AnimationListener listener) {
    new BlinkAnimation(view).setListener(listener).animate();
  }

  /**
   * 
   * @param view
   * @param bounces
   *          value of {times of playing bounce}
   * @param duration
   *          {@value Animation.DURATION_SHORT}
   * @param listener
   */
  public static void playBounce(View view, int bounces, int duration, AnimationListener listener) {
    new BounceAnimation(view).setNumOfBounces(bounces).setDuration(duration).setListener(listener).animate();
  }

  public static void playFlipHorizontal(View view, AnimationListener listener) {
    new FlipHorizontalAnimation(view).setListener(listener).animate();
  }

  public static void playFlipHorizontalToView(View view, View flipToView) {
    new FlipHorizontalToAnimation(view).setFlipToView(flipToView).setInterpolator(new LinearInterpolator()).animate();
  }

  public static void playFlipVertical(View view, AnimationListener listener) {
    new FlipVerticalAnimation(view).setListener(listener).animate();
  }

  public static void playFlipVerticalToView(View view, View flipToView) {
    new FlipVerticalToAnimation(view).setFlipToView(flipToView).setInterpolator(new LinearInterpolator()).animate();
  }

  /**
   * 
   * @param view
   * @param folds
   * @param duration
   *          {@value Animation.DURATION_SHORT} or a time like 3000
   * @param orientation
   *          {@value Orientation.HORIZONTAL|Orientation.VERTICAL}
   */
  public static void playFold(View view, int folds, int duration, Orientation orientation) {
    new FoldAnimation(view).setNumOfFolds(folds).setDuration(duration).setOrientation(orientation).animate();
  }

  /**
   * 
   * @param view
   * @param folds
   * @param duration
   *          {@value Animation.DURATION_SHORT} or a time like 3000
   * @param orientation
   *          {@value Orientation.HORIZONTAL|Orientation.VERTICAL}
   */
  public static void playUnFold(View view, int folds, int duration, Orientation orientation) {
    new UnfoldAnimation(view).setNumOfFolds(folds).setDuration(duration).setOrientation(orientation).animate();
  }

  public static void playHighlight(View view, AnimationListener listener) {
    new HighlightAnimation(view).setListener(listener).animate();
  }

  public static void playPath(View view, ArrayList<Point> points, int duration, AnimationListener listener) {
    new PathAnimation(view).setPoints(points).setDuration(duration).setListener(listener).animate();
  }

  public static void playPuffIn(View view) {
    new PuffInAnimation(view).animate();
  }

  public static void playScaleIn(View view) {
    new ScaleInAnimation(view).animate();
  }

  public static void playScaleOut(View view) {
    new ScaleOutAnimation(view).animate();
  }

  public static void playPuffOut(View view) {
    new PuffOutAnimation(view).animate();
  }

  public static void playRotation(View view, int pivot, AnimationListener listener) {
    new RotationAnimation(view).setPivot(pivot).setListener(listener).animate();
  }

  /**
   * 
   * @param view
   * @param shakes
   *          the value of shake times
   * @param duration
   *          {@value Animation.DURATION_SHORT} or a time like 3000
   * @param listener
   */
  public static void playShake(View view, int shakes, int duration, AnimationListener listener) {
    new ShakeAnimation(view).setNumOfShakes(shakes).setDuration(duration).setListener(listener).animate();
  }

  /**
   * 
   * @param view
   * @param direction
   *          {@value Animation.DIRECTION_UP|DIRECTION_UP}
   */
  public static void playSlideIn(View view, int direction) {
    new SlideInAnimation(view).setDirection(direction).animate();
  }

  /**
   * 
   * @param view
   * @param direction
   *          {@value Animation.DIRECTION_UP|DIRECTION_UP}
   */
  public static void playSlideInUnderneath(View view, int direction) {
    new SlideInUnderneathAnimation(view).setDirection(direction).animate();
  }

  /**
   * 
   * @param view
   * @param direction
   *          {@value Animation.DIRECTION_UP|DIRECTION_UP}
   */
  public static void playSlideOut(View view, int direction) {
    new SlideOutAnimation(view).setDirection(direction).animate();
  }

  /**
   * 
   * @param view
   * @param direction
   *          {@value Animation.DIRECTION_UP|DIRECTION_UP}
   */
  public static void playSlideOutUnderneath(View view, int direction) {
    new SlideOutUnderneathAnimation(view).setDirection(direction).animate();
  }

  /**
   * 
   * @param view
   * @param target
   *          target view transfer to
   */
  public static void playTransfer(View view, View target) {
    new TransferAnimation(view).setDestinationView(target).animate();
  }

}
