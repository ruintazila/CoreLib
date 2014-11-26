/*
 * CrashHandler.java
 * classes : com.ruint.core.exception.CrashHandler
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:21:10
 */
package com.ruint.core.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Properties;
import java.util.TreeSet;

import android.content.Context;
import android.os.Looper;
import android.text.format.Time;
import android.view.Gravity;
import android.widget.Toast;

import com.ruint.core.utils.preferences.CorePreferences;

/**
 * com.ruint.core.exception.CrashHandler
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:21:10
 */
public class CrashHandler implements UncaughtExceptionHandler {
  private Thread.UncaughtExceptionHandler mDefaultHandler;
  private static CrashHandler INSTANCE;
  private Context mContext;
  private Properties mDeviceCrashInfo = new Properties();
  @SuppressWarnings("unused")
  private static final String VERSION_NAME = "versionName";
  @SuppressWarnings("unused")
  private static final String VERSION_CODE = "versionCode";
  private static final String STACK_TRACE = "STACK_TRACE";
  private static final String CRASH_REPORTER_EXTENSION = ".cr";

  private CrashHandler() {
  }

  public static CrashHandler getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CrashHandler();
    }
    return INSTANCE;
  }

  public void init(Context ctx) {
    mContext = ctx;
    mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  /** 
  */
  @Override
  public void uncaughtException(Thread thread, Throwable ex) {
    if (!handleException(ex) && mDefaultHandler != null) {
      mDefaultHandler.uncaughtException(thread, ex);
    } else {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        CorePreferences.ERROR("Error : ", e);
      }
      android.os.Process.killProcess(android.os.Process.myPid());
      System.exit(0);
      // ActivityManager activityMgr= (ActivityManager)
      // mContext.getSystemService(Context.ACTIVITY_SERVICE);
      // activityMgr.restartPackage(mContext.getPackageName());
    }
  }

  private boolean handleException(final Throwable ex) {
    CorePreferences.ERROR("handleException", ex);
    if (ex == null) {
      return true;
    }

    new Thread() {
      @Override
      public void run() {
        Looper.prepare();
        Toast toast = Toast.makeText(mContext, "程序发生致命异常,请检查系统版本是否兼容或者联系我们！", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        // 将错误信息发送到服务器，catch所有异常，防止CrashHandler再次被调用
        try {
          // HouseAnalyse.onCrash(mContext, ex);
        } catch (Throwable t) {
          CorePreferences.ERROR("Failure in crash handler.", t);
        }
        Looper.loop();
      }
    }.start();

    return true;
  }

  public void sendPreviousReportsToServer() {
    sendCrashReportsToServer(mContext);
  }

  private void sendCrashReportsToServer(Context ctx) {
    String[] crFiles = getCrashReportFiles(ctx);
    if (crFiles != null && crFiles.length > 0) {
      TreeSet<String> sortedFiles = new TreeSet<String>();
      sortedFiles.addAll(Arrays.asList(crFiles));
      for (String fileName : sortedFiles) {
        File cr = new File(ctx.getFilesDir(), fileName);
        postReport(cr);
        cr.delete();//
      }
    }
  }

  private void postReport(File file) {
  }

  private String[] getCrashReportFiles(Context ctx) {
    File filesDir = ctx.getFilesDir();
    FilenameFilter filter = new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.endsWith(CRASH_REPORTER_EXTENSION);
      }
    };
    return filesDir.list(filter);
  }

  @SuppressWarnings("unused")
  private String saveCrashInfoToFile(Throwable ex) {
    Writer info = new StringWriter();
    PrintWriter printWriter = new PrintWriter(info);
    ex.printStackTrace(printWriter);
    Throwable cause = ex.getCause();
    while (cause != null) {
      cause.printStackTrace(printWriter);
      cause = cause.getCause();
    }
    String result = info.toString();
    printWriter.close();
    mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
    mDeviceCrashInfo.put(STACK_TRACE, result);
    try {
      // long timestamp = System.currentTimeMillis();
      Time t = new Time("GMT+8");
      t.setToNow();
      int date = t.year * 10000 + t.month * 100 + t.monthDay;
      int time = t.hour * 10000 + t.minute * 100 + t.second;
      String fileName = "crash-" + date + "-" + time + CRASH_REPORTER_EXTENSION;
      FileOutputStream trace = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
      mDeviceCrashInfo.store(trace, "");
      trace.flush();
      trace.close();
      return fileName;
    } catch (Exception e) {
      CorePreferences.ERROR("an error occured while writing report file...", e);
    }
    return null;
  }

}
