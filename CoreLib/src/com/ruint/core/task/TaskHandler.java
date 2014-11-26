/*
 * TaskHandler.java
 * classes : com.ruint.core.task.TaskHandler
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-25 上午11:13:43
 */
package com.ruint.core.task;

/**
 * com.ruint.core.task.TaskHandler
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:13:43
 */
public interface TaskHandler {
  boolean supportPause();

  boolean supportResume();

  boolean supportCancel();

  void pause();

  void resume();

  void cancel();

  boolean isPaused();

  boolean isCancelled();
}
