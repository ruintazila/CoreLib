/*
 * HttpApiException.java
 * classes : com.ruint.core.exception.HttpApiException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:31:19
 */
package com.ruint.core.exception;

/**
 * com.ruint.core.exception.HttpApiException
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:31:19
 */
public class HttpApiException extends BaseException {

  private static final long serialVersionUID = -4841368571575802796L;

  public HttpApiException() {
    super();
  }

  public HttpApiException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public HttpApiException(String detailMessage) {
    super(detailMessage);
  }

  public HttpApiException(Throwable throwable) {
    super(throwable);
  }

}
