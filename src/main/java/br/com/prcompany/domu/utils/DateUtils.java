package br.com.prcompany.domu.utils;


import org.apache.commons.lang3.StringUtils;

public final class DateUtils {

  private DateUtils() {
  }

  public static String transformaDataAvenue(String data) {
    String[] split = data.trim().split("/");
    return StringUtils.leftPad(split[0], 2, '0') + "/" + StringUtils.leftPad(split[1], 2, '0') + "/"
        + split[2];
  }

}
