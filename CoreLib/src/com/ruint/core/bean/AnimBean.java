/*
 * AnimBean.java
 * classes : com.ruint.core.bean.AnimBean
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-19 下午1:37:43
 */
package com.ruint.core.bean;

/**
 * com.ruint.core.bean.AnimBean
 * 
 * @author ruint <br/>
 *         create at 2014-11-19 下午1:37:43
 */
public class AnimBean {
  private int in = 0;
  private int out = 0;

  public int getIn() {
    return in;
  }

  public void setIn(int in) {
    this.in = in;
  }

  public int getOut() {
    return out;
  }

  public void setOut(int out) {
    this.out = out;
  }

  public AnimBean(int in, int out) {
    super();
    this.in = in;
    this.out = out;
  }
}
