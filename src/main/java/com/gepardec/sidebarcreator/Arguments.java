package com.gepardec.sidebarcreator;

import java.util.ArrayList;
import java.util.List;

public class Arguments {
  private String wikiMainFolder = null;
  private boolean helpRequested = false;
  private List<String> excludedSubfolders = new ArrayList<>();

  public String getWikiMainFolder() {
    return wikiMainFolder;
  }

  public void setWikiMainFolder(String wikiMainFolder) {
    this.wikiMainFolder = wikiMainFolder;
  }

  public boolean isHelpRequested() {
    return helpRequested;
  }

  public void setHelpRequested(boolean helpRequested) {
    this.helpRequested = helpRequested;
  }

  public List<String> getExcludedSubfolders() {
    return excludedSubfolders;
  }

  public void setExcludedSubfolders(List<String> excludedSubfolders) {
    this.excludedSubfolders = excludedSubfolders;
  }
}
