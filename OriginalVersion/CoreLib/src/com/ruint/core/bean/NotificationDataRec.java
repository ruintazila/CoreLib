/*
 * NotificationDataRec.java
 * classes : com.ruint.core.bean.NotificationDataRec
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 下午3:18:25
 */
package com.ruint.core.bean;


/**
 * com.ruint.core.bean.NotificationDataRec
 * @author ruint <br/>
 * create at 2014-11-12 下午3:18:25
 */
public class NotificationDataRec extends CorePreferencesDTO<NotificationDataRec>{
  private static final long serialVersionUID = -829039834277394475L;
  private String id;
  private String title;
  private String message;
  private String uri;
  
  public NotificationDataRec() {
  }

  public NotificationDataRec(NotificationData notification) {
    super();
    this.id = notification.getId();
    this.title = notification.getTitle();
    this.message = notification.getMessage();
    this.uri = notification.getUri();
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

  @Override
  public boolean isSame(NotificationDataRec o) {
    return id.equals(o.getId());
  }
}
