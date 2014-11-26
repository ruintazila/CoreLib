package com.ruint.core.task;

/**
 * com.ruint.core.task
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:13:43
 */
public class PriorityRunnable extends PriorityObject<Runnable> implements Runnable {

  public PriorityRunnable(Priority priority, Runnable obj) {
    super(priority, obj);
  }

  @Override
  public void run() {
    this.obj.run();
  }
}
