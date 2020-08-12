package com.gepardec.sidebarcreator.common;

import java.io.File;

public abstract class MdFileUtils {

  public static final String MD_ENDING = "md";
  public static final String SIDEBAR_NAME = "_Sidebar";

  public static boolean isMdFile(File file) {
    if(!file.isFile()){
      return false;
    }

    return file.getAbsolutePath().endsWith(MD_ENDING);
  }

  public static boolean isSidebarFile(File file) {
    if(!isMdFile(file)){
      return false;
    }

    return (SIDEBAR_NAME + "." + MD_ENDING).equals(file.getName());
  }
}
