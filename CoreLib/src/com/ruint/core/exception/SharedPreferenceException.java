/*
 * SharedPreferenceException.java
 * classes : ruint.core.exception.SharedPreferenceException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午1:52:55
 */
package com.ruint.core.exception;

/**
 * ruint.core.exception.SharedPreferenceException
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午1:52:55
 */
public class SharedPreferenceException extends BaseException {

  private static final long serialVersionUID = -2405508880959384484L;

  public SharedPreferenceException() {
    super();
  }

  public SharedPreferenceException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public SharedPreferenceException(String detailMessage) {
    super(detailMessage);
  }

  public SharedPreferenceException(Throwable throwable) {
    super(throwable);
  }

}
