/*
 * CoreConfig.java
 * classes : ruint.core.bean.CoreConfig
 * @author ruint
 * V 1.0.0
 * Create at 2014-11-12 上午11:11:24
 */
package com.ruint.core.bean;

/**
 * ruint.core.bean.CoreConfig
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:11:24
 */
public class CoreConfig extends CorePreferencesDTO<CoreConfig> {

  private static final long serialVersionUID = -3835410248974340454L;

  private boolean isDebug;

  /*
   * app relatives
   */
  private String appTag;
  private String appPath;
  private String appName;

  private boolean isOpenBaiduStat;

  /*
   * bMap api key
   */
  private String appBaiduMapKey;

  private String defaultCity;
  private boolean defaultCityForce;

  /*
   * Analyse params
   */
  private boolean isAnalyse;
  private String analyseUrl;
  private int analyseBufferSize = 10;
  private String analyseChannel;

  /*
   * @see ruint.core.bean.CorePreferencesDTO#isSame(java.lang.Object)
   */
  @Override
  public boolean isSame(CoreConfig o) {
    return false;
  }

  public boolean isDebug() {
    return this.isDebug;
  }

  public void setDebug(boolean isDebug) {
    this.isDebug = isDebug;
  }

  public String getAppTag() {
    return this.appTag;
  }

  public void setAppTag(String appTag) {
    this.appTag = appTag;
  }

  public String getAppPath() {
    return this.appPath;
  }

  public void setAppPath(String appPath) {
    this.appPath = appPath;
  }

  public String getAppName() {
    return this.appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public boolean isOpenBaiduStat() {
    return this.isOpenBaiduStat;
  }

  public void setOpenBaiduStat(boolean isOpenBaiduStat) {
    this.isOpenBaiduStat = isOpenBaiduStat;
  }

  public String getAppBaiduMapKey() {
    return this.appBaiduMapKey;
  }

  public void setAppBaiduMapKey(String appBaiduMapKey) {
    this.appBaiduMapKey = appBaiduMapKey;
  }

  public String getDefaultCity() {
    return this.defaultCity;
  }

  public void setDefaultCity(String defaultCity) {
    this.defaultCity = defaultCity;
  }

  public boolean isDefaultCityForce() {
    return this.defaultCityForce;
  }

  public void setDefaultCityForce(boolean defaultCityForce) {
    this.defaultCityForce = defaultCityForce;
  }

  public boolean isAnalyse() {
    return this.isAnalyse;
  }

  public void setAnalyse(boolean isAnalyse) {
    this.isAnalyse = isAnalyse;
  }

  public String getAnalyseUrl() {
    return this.analyseUrl;
  }

  public void setAnalyseUrl(String analyseUrl) {
    this.analyseUrl = analyseUrl;
  }

  public int getAnalyseBufferSize() {
    return this.analyseBufferSize;
  }

  public void setAnalyseBufferSize(int analyseBufferSize) {
    this.analyseBufferSize = analyseBufferSize;
  }

  public String getAnalyseChannel() {
    return this.analyseChannel;
  }

  public void setAnalyseChannel(String analyseChannel) {
    this.analyseChannel = analyseChannel;
  }

}
