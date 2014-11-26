/*
 * ReflectException.java
 * classes : ruint.core.reflect.ReflectException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午10:52:08
 */
package com.ruint.core.exception;

/**
 * ruint.core.reflect.ReflectException
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午10:52:08
 */
public class ReflectException extends BaseException {

  private static final long serialVersionUID = -958592019185928390L;

  public ReflectException() {
    super();
  }

  public ReflectException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public ReflectException(String detailMessage) {
    super(detailMessage);
  }

  public ReflectException(Throwable throwable) {
    super(throwable);
  }
}
