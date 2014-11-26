package com.ruint.core.utils.volley;

import java.io.File;
import java.util.Map;

import org.apache.http.entity.mime.content.ByteArrayBody;

/**
 * ruint.core.utils.volley.VolleyUtils
 * 
 * @author ruint <br/>
 *         create at 2014-11-12 上午11:18:38
 */
public interface MultiPartRequest {

  public void addFile(String param, File file);

  public void addByte(String param, ByteArrayBody body);

  public void addString(String param, String content);

  public Map<String, File> getFile();

  public Map<String, ByteArrayBody> getByte();

  public Map<String, String> getString();
}