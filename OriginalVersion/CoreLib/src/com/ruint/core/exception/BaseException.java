/*
 * BaseException.java
 * classes : com.ruint.core.exception.BaseException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-17 上午11:14:12
 */
package com.ruint.core.exception;

/**
 * com.ruint.core.exception.BaseException
 * 
 * @author ruint <br/>
 *         create at 2014-11-17 上午11:14:12
 */
public class BaseException extends Exception {
  private static final long serialVersionUID = 1L;

  public BaseException() {
  }

  public BaseException(String detailMessage) {
    super(detailMessage);
  }

  public BaseException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public BaseException(Throwable throwable) {
    super(throwable);
  }
}
