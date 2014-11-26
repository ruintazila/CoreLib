/*
 * NetworkUnavailableException.java
 * classes : com.ruint.core.exception.NetworkUnavailableException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:38:42
 */
package com.ruint.core.exception;

/**
 * com.ruint.core.exception.NetworkUnavailableException
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:38:42
 */
public class NetworkUnavailableException extends BaseException {
  private static final long serialVersionUID = 7606002721650122388L;

  public NetworkUnavailableException() {
    super();
  }

  public NetworkUnavailableException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public NetworkUnavailableException(String detailMessage) {
    super(detailMessage);
  }

  public NetworkUnavailableException(Throwable throwable) {
    super(throwable);
  }
}
