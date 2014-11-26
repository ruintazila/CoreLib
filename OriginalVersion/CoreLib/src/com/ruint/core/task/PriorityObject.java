package com.ruint.core.task;

/**
 * com.ruint.core.task
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:13:43
 */
public class PriorityObject<E> {

  public final Priority priority;
  public final E obj;

  public PriorityObject(Priority priority, E obj) {
    this.priority = priority == null ? Priority.DEFAULT : priority;
    this.obj = obj;
  }
}
