/*
 * ViewPagerTransformerUtil.java
 * classes : com.ruint.core.view.viewpager.ViewPagerTransformerUtil
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-13 下午4:28:59
 */
package com.ruint.core.utils.ui;

import java.util.ArrayList;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;

import com.ruint.lib.viewpager.transforms.AccordionTransformer;
import com.ruint.lib.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ruint.lib.viewpager.transforms.CubeInTransformer;
import com.ruint.lib.viewpager.transforms.CubeOutTransformer;
import com.ruint.lib.viewpager.transforms.DefaultTransformer;
import com.ruint.lib.viewpager.transforms.DepthPageTransformer;
import com.ruint.lib.viewpager.transforms.FlipHorizontalTransformer;
import com.ruint.lib.viewpager.transforms.FlipVerticalTransformer;
import com.ruint.lib.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ruint.lib.viewpager.transforms.RotateDownTransformer;
import com.ruint.lib.viewpager.transforms.RotateUpTransformer;
import com.ruint.lib.viewpager.transforms.StackTransformer;
import com.ruint.lib.viewpager.transforms.TabletTransformer;
import com.ruint.lib.viewpager.transforms.ZoomInTransformer;
import com.ruint.lib.viewpager.transforms.ZoomOutSlideTransformer;
import com.ruint.lib.viewpager.transforms.ZoomOutTranformer;

/**
 * com.ruint.core.view.viewpager.ViewPagerTransformerUtil
 * 
 * @author ruint <br/>
 *         create at 2014-11-13 下午4:28:59
 */
public class ViewPagerTransformerUtils {

  private static ArrayList<TransformerItem> TRANSFORM_CLASSES;

  private static void initTransformClasses() {
    TRANSFORM_CLASSES = new ArrayList<TransformerItem>();
    TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class));
    TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class));
  }

  public static void setPagerTransformer(ViewPager mPager, int position) {
    if (TRANSFORM_CLASSES == null || TRANSFORM_CLASSES.size() == 0) {
      initTransformClasses();
    }
    try {
      mPager.setPageTransformer(true, TRANSFORM_CLASSES.get(position).clazz.newInstance());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static final class TransformerItem {

    final String title;
    final Class<? extends PageTransformer> clazz;

    public TransformerItem(Class<? extends PageTransformer> clazz) {
      this.clazz = clazz;
      title = clazz.getSimpleName();
    }

    @Override
    public String toString() {
      return title;
    }
  }
}
