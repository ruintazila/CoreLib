/*
 * HttpParseException.java
 * classes : com.ruint.core.exception.HttpParseException
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午2:37:56
 */
package com.ruint.core.exception;

/**
 * com.ruint.core.exception.HttpParseException
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午2:37:56
 */
public class HttpParseException extends BaseException {

  private static final long serialVersionUID = -6056730757564882530L;

  public HttpParseException() {
    super();
  }

  public HttpParseException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public HttpParseException(String detailMessage) {
    super(detailMessage);
  }

  public HttpParseException(Throwable throwable) {
    super(throwable);
  }
}
