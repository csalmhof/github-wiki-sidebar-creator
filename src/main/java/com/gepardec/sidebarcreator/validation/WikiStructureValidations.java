package com.gepardec.sidebarcreator.validation;

import com.gepardec.sidebarcreator.validation.exceptions.WikiStructureException;
import com.gepardec.sidebarcreator.Arguments;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.gepardec.sidebarcreator.common.WikiFolderStructureUtils.getAllNotExcludedSubdirectories;
import static com.gepardec.sidebarcreator.common.WikiFolderStructureUtils.getContentMdFiles;

public class WikiStructureValidations {

  public static void validateWikiStructure(Arguments arguments) throws WikiStructureException, IOException {
    validateHomeMdFileExists(arguments);
    validateOnlyOneContentMdFileBesideSidebarInDirectories(arguments);
  }

  private static void validateHomeMdFileExists(Arguments arguments) throws WikiStructureException {
    File homeMdFile = new File(arguments.getWikiMainFolder() + File.separator + "Home.md");
    if (!homeMdFile.exists()) {
      throw new WikiStructureException(arguments.getWikiMainFolder() + ": Home.md file does not exist");
    }
  }

  private static void validateOnlyOneContentMdFileBesideSidebarInDirectories(Arguments arguments) throws IOException, WikiStructureException {
    for(String subdirectory : getAllNotExcludedSubdirectories(arguments.getWikiMainFolder(), arguments.getExcludedSubfolders())) {
      validateOnlyOneMdFileBesideSidebarInDirectory(subdirectory);
    }
  }



  private static void validateOnlyOneMdFileBesideSidebarInDirectory(String directoryPath) throws WikiStructureException {
    File directory = new File(directoryPath);
    List<String> contentMdFiles = getContentMdFiles(directory);

    if (contentMdFiles.size() == 0) {
      throw new WikiStructureException("No .md-File found in " + directoryPath);
    }

    if (contentMdFiles.size() > 1) {
      String exceptionString = "Multiple .md-Files found in " + directoryPath + ": ";
      String filenames = contentMdFiles.stream().map(s -> s.substring(directoryPath.length())).reduce((s1, s2) -> s1 + ", " + s2).get();
      exceptionString += filenames;
      throw new WikiStructureException(exceptionString);
    }
  }


}
