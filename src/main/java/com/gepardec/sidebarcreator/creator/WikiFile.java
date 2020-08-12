package com.gepardec.sidebarcreator.creator;

import java.io.File;

import static com.gepardec.sidebarcreator.common.StringUtils.removeLeadingDigitsUnderscoresAndHyphens;

public class WikiFile {
  private String displayName;
  private String fileName;
  private String parentFolder;

  public WikiFile(String filePath) {
    this.displayName = initDisplayName(filePath);
    this.fileName = initFileName(filePath);
    this.parentFolder = new File(filePath).getParentFile().getAbsolutePath();
  }

  public WikiFile(File file) {
    this(file.getAbsolutePath());
  }

  private static String initDisplayName(String filepath) {
    String result = initFileName(filepath);
    result = result.replace("-", " ");

    result = removeLeadingDigitsUnderscoresAndHyphens(result);

    return result.trim();
  }

  private static String initFileName(String filepath) {
    return filepath.substring(filepath.lastIndexOf(File.separator)+1, filepath.lastIndexOf("."));
  }

  public File getAsFile() {
    return new File(getFilePath());
  }

  public String getParentFolder() {
    return parentFolder;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getFileName() {
    return fileName;
  }

  public String getFilePath() {
    return parentFolder + File.separator + fileName + ".md";
  }
}
