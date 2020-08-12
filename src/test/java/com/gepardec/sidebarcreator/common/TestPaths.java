package com.gepardec.sidebarcreator.common;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

public interface TestPaths {
  String RESOURCE_PATH = "src" + File.separator + "test" + File.separator + "resources";
  String TEST_WIKI = RESOURCE_PATH + File.separator + "testwiki.wiki";
  String FIRST_SUB_FOLDER = TEST_WIKI + File.separator + "01_FirstSubPart";
  String FIRST_SUBSUB_FOLDER = FIRST_SUB_FOLDER + File.separator + "01_FirstSubSubPart";
  String SECOND_SUB_FOLDER = TEST_WIKI + File.separator + "02_SecondSubPart";
  String SECOND_SUBSUB_FOLDER = SECOND_SUB_FOLDER + File.separator + "01_SecondSubSubPart";

  String HOME_FILE = TEST_WIKI + File.separator + "Home.md";
  String FIRST_SUB_FILE = FIRST_SUB_FOLDER + File.separator + "01_First-Sub-Part.md";

  String EXCLUDED_FOLDER_TOP = TEST_WIKI + File.separator + "excludedDirectory";
  String EXCLUDED_FOLDER_FIRST_SUB = FIRST_SUB_FOLDER + File.separator + "excludedDirectory";
  String EXCLUDED_FOLDER_FIRST_SUBSUB = FIRST_SUBSUB_FOLDER + File.separator + "excludedDirectory";
  String EXCLUDED_FOLDER_SECOND_SUBSUB = SECOND_SUBSUB_FOLDER + File.separator + "excludedDirectory";

  List<String> EXCLUDED_FOLDERS_ALL = Lists.newArrayList(EXCLUDED_FOLDER_TOP, EXCLUDED_FOLDER_FIRST_SUB, EXCLUDED_FOLDER_FIRST_SUBSUB, EXCLUDED_FOLDER_SECOND_SUBSUB);
}
