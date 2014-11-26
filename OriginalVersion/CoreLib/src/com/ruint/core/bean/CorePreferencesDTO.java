/*
 * CorePreferencesDTO.java
 * classes : ruint.core.bean.CorePreferencesDTO
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午11:09:21
 */
package com.ruint.core.bean;

/**
 * ruint.core.bean.CorePreferencesDTO
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:09:21
 */
public abstract class CorePreferencesDTO<T> extends BaseBean {

  private static final long serialVersionUID = 2756388073240443113L;

  public abstract boolean isSame(T o);
}
