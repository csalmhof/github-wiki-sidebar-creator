package com.gepardec.sidebarcreator.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class WikiFolderStructureUtils {

  public static List<String> getAllSubdirectoriesSortedAlphabetically(String mainFolder) throws IOException {
    return Files.walk(Paths.get(mainFolder))
        .filter(Files::isDirectory)
        .map(Path::toString)
        .filter(pathString -> !pathString.equals(mainFolder))
        .sorted()
        .collect(Collectors.toList());
  }

  public static List<String> getAllNotExcludedSubdirectories(String mainFolder, List<String> excludedFolders) throws IOException {
    return WikiFolderStructureUtils.getAllSubdirectoriesSortedAlphabetically(mainFolder).stream()
        .filter(WikiFolderStructureUtils.excludeGitDirectory())
        .filter(WikiFolderStructureUtils.excludeFolders(excludedFolders))
        .collect(Collectors.toList());
  }

  public static Predicate<String> excludeFolders(List<String> excludedFolders) {
    return pathString -> !excludedFolders.contains(pathString);
  }

  public static Predicate<String> excludeGitDirectory() {
    return pathString -> !pathString.contains(".git");
  }

  public static List<String> getMdFilesInDirectory(File directory) {
    return Arrays.stream(directory.listFiles())
        .filter(f -> !f.isDirectory())
        .filter(f -> f.getAbsolutePath().endsWith(".md"))
        .map(File::getAbsolutePath)
        .collect(Collectors.toList());
  }

  public static List<String> getContentMdFiles(File directory) {
    return getMdFilesInDirectory(directory).stream()
        .filter(WikiFolderStructureUtils.isNoSidebarFile())
        .collect(Collectors.toList());
  }

  public static Predicate<String> isNoSidebarFile() {
    return file -> !file.endsWith("_Sidebar.md");
  }

}
