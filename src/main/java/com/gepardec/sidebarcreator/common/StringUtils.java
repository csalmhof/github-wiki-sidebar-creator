package com.gepardec.sidebarcreator.common;

import java.util.List;

import static java.lang.Character.isDigit;

public abstract class StringUtils {

  public static String removeLeadingDigitsUnderscoresAndHyphens(String s) {
    if(s.isEmpty()) {
      throw new IllegalArgumentException("String cannot be empty");
    }

    String result = s;
    while (stringStartsWithDigitsUnderscoreOrHyphen(result)) {
      result = result.substring(1);
    }
    return result;
  }

  public static boolean stringStartsWithDigitsUnderscoreOrHyphen(String s) {
    if(isDigit(s.charAt(0))) {
      return true;
    }

    if ('_' == s.charAt(0)) {
      return true;
    }

    if ('-' == s.charAt(0)) {
      return true;
    }

    return false;
  }

  public static boolean anyStringInListIsContainedInOtherString(List<String> strings, String other) {
    return strings.stream().anyMatch(s -> s.contains(other));
  }


}
