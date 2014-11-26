/*
 * AlertUtils.java
 * classes : com.ruint.core.utils.ui.AlertUtils
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-14 下午4:55:27
 */
package com.ruint.core.utils.ui;

import android.content.Context;
import android.text.TextUtils;

import com.ruint.core.view.SweetAlert.SweetAlertDialog;
import com.ruint.core.view.SweetAlert.SweetAlertDialog.OnSweetClickListener;

/**
 * com.ruint.core.utils.ui.AlertUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-14 下午4:55:27
 */
public class AlertUtils {
  public static final int TYPE_BASIC = 0x44;
  public static final int TYPE_UNDER_TEXT = 0x45;
  public static final int TYPE_ERROR = 0x46;
  public static final int TYPE_SUCCESS = 0x47;
  public static final int TYPE_WARNING_SINGLE = 0x48;
  public static final int TYPE_WARNING_DOUBLE = 0x49;

  /**
   * @param context
   * @param title
   * @param content
   * @param type
   *          Need this param when ur building the AlertDialog with type
   *          {@link #TYPE_BASIC} & {@link #TYPE_UNDER_TEXT} &
   *          {@link #TYPE_ERROR}& {@link #TYPE_SUCCESS}
   */
  public static void showSimpleAlertDialog(Context context, String title, String content, int type) {
    SweetAlertDialog dialog = null;

    switch (type) {
      case TYPE_BASIC:
        dialog = new SweetAlertDialog(context);
        break;
      case TYPE_UNDER_TEXT:
        dialog = new SweetAlertDialog(context);
        dialog.setContentText(content);
        break;
      case TYPE_ERROR:
        dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setContentText(content);
        break;
      case TYPE_SUCCESS:
        dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setContentText(content).show();
        break;
    }
    if (!TextUtils.isEmpty(title)) {
      dialog.setTitleText(title);
    } else {
      // default title "Here's a message!"
    }
    dialog.show();
  }

  /**
   * @Deprecated use
   *             {@link #showConfirmDialog(Context, String, String, String, String, OnSweetClickListener, OnSweetClickListener, OnSweetClickListener)}
   *             instead.
   * @param context
   * @param title
   * @param content
   * @param confirmText
   * @param cancelText
   * @param type
   * @param listener
   */
  @Deprecated
  public static void showWarningDialog(Context context, String title, String content, String confirmText,
      String cancelText, int type, SweetAlertDialog.OnSweetClickListener listener) {
    switch (TYPE_BASIC) {
      case TYPE_WARNING_SINGLE:
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText(title).setContentText(content)
            .setConfirmText(confirmText).setConfirmClickListener(listener
            /*
             * new SweetAlertDialog.OnSweetClickListener() {
             * 
             * @Override public void onClick(SweetAlertDialog sDialog) { //
             * reuse previous dialog instance
             * sDialog.setTitleText("Deleted!").setContentText
             * ("Your imaginary file has been deleted!")
             * .setConfirmText("OK").setConfirmClickListener
             * (null).changeAlertType(SweetAlertDialog.SUCCESS_TYPE); } }
             */
            ).show();
        break;
      case TYPE_WARNING_DOUBLE:
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText(title).setContentText(content)
            .setCancelText(cancelText).setConfirmText(confirmText).showCancelButton(true)
            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sDialog) {
                // reuse previous dialog instance, keep widget user state, reset
                // them if you need
                sDialog.setTitleText("Cancelled!").setContentText("Your imaginary file is safe :)")
                    .setConfirmText("OK").showCancelButton(false).setCancelClickListener(null)
                    .setConfirmClickListener(null).changeAlertType(SweetAlertDialog.ERROR_TYPE);

                // or you can new a SweetAlertDialog to show
                /*
                 * sDialog.dismiss(); new
                 * SweetAlertDialog(SampleActivity.context,
                 * SweetAlertDialog.ERROR_TYPE) .setTitleText("Cancelled!")
                 * .setContentText("Your imaginary file is safe :)")
                 * .setConfirmText("OK") .show();
                 */
              }
            }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sDialog) {
                sDialog.setTitleText("Deleted!").setContentText("Your imaginary file has been deleted!")
                    .setConfirmText("OK").showCancelButton(false).setCancelClickListener(null)
                    .setConfirmClickListener(null).changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
              }
            }).show();
        break;
    }
  }

  /**
   * 
   * @param context
   * @param title
   * @param content
   * @param confirmText
   * @param cancelText
   * @param cancelClickListener
   *          Things to do after u perfermClick the cancel button.Usually
   *          refector
   *          {@link #refectorDialog(SweetAlertDialog, String, String, OnSweetClickListener, int)}
   *          a dialog with type {@value SweetAlertDialog.ERROR_TYPE}. Can be
   *          null,then will provide a default refector that do nothing but a
   *          tip.
   * @param confirmClickListener
   *          Things to do after u perfermClick the confirm button.Usually
   *          refector
   *          {@link #refectorDialog(SweetAlertDialog, String, String, OnSweetClickListener, int)}
   *          a dialog with type {@value SweetAlertDialog.SUCCESS_TYPE}. Can be
   *          null,then will provide a default refector that do nothing but a
   *          tip.
   * @param confirmInnerListener
   *          Things to do after u perfermClick the refected confirm button in
   *          confirmClickListener.Usually refector
   *          {@link #refectorDialog(SweetAlertDialog, String, String, OnSweetClickListener, int)}
   *          a dialog with type {@value SweetAlertDialog.SUCCESS_TYPE}. Can be
   *          null,then will provide a default refector that do nothing but a
   *          tip.
   */
  public static void showConfirmDialog(Context context, String title, String content, String confirmText,
      String cancelText, OnSweetClickListener cancelClickListener, OnSweetClickListener confirmClickListener,
      final OnSweetClickListener confirmInnerListener) {
    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
        .setTitleText(title)
        .setContentText(content)
        .setCancelText(cancelText == null ? "Cancel" : cancelText)
        .setConfirmText(confirmText == null ? "OK" : confirmText)
        .showCancelButton(true)
        .setCancelClickListener(
            cancelClickListener != null ? cancelClickListener : new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sDialog) {
                // reuse previous dialog instance, keep widget user state, reset
                // them if you need
                refectorDialog(sDialog, "Cancelled!", "Nothing was done!", null, SweetAlertDialog.ERROR_TYPE);
                // or you can new a SweetAlertDialog to show
                /*
                 * sDialog.dismiss(); new
                 * SweetAlertDialog(SampleActivity.context,
                 * SweetAlertDialog.ERROR_TYPE) .setTitleText("Cancelled!")
                 * .setContentText("Your imaginary file is safe :)")
                 * .setConfirmText("OK") .show();
                 */
              }
            })
        .setConfirmClickListener(
            confirmClickListener != null ? confirmClickListener : new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sDialog) {
                refectorDialog(sDialog, "Done!", "finished!", confirmInnerListener, SweetAlertDialog.SUCCESS_TYPE);
              }
            }).show();
  }

  public static void showImageDialog(Context context, String title, String content, int resId) {
    new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).setTitleText(title).setContentText(content)
        .setCustomImage(resId).show();
  }

  /**
   * invoke by
   * {@link #showConfirmDialog(Context, String, String, String, String, OnSweetClickListener, OnSweetClickListener, OnSweetClickListener)}
   * Use to refector the @param sDialog
   * 
   * @param sDialog
   * @param title
   * @param content
   * @param listener
   * @param type
   */
  public static void refectorDialog(SweetAlertDialog sDialog, String title, String content,
      OnSweetClickListener listener, int type) {
    sDialog.setTitleText(title).setContentText(content).setConfirmText("OK").showCancelButton(false)
        .setCancelClickListener(null).setConfirmClickListener(listener).changeAlertType(type);
  }
}
