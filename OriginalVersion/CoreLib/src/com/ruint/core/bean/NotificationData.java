/*
 * NotificationData.java
 * classes : com.ruint.core.bean.NotificationData
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:19:06
 */
package com.ruint.core.bean;

import java.io.Serializable;

/**
 * com.ruint.core.bean.NotificationData
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 下午3:19:06
 */
public class NotificationData implements Serializable {
  private static final long serialVersionUID = 6417081171056763954L;

  public String id;
  public String title;
  public String message;
  public String uri;

  public NotificationData() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public NotificationData(String id, String title, String message, String uri) {
    super();
    this.id = id;
    this.title = title;
    this.message = message;
    this.uri = uri;
  }
}
