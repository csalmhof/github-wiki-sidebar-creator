package com.gepardec.sidebarcreator.creator;

import com.gepardec.sidebarcreator.validation.exceptions.WikiStructureException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.gepardec.sidebarcreator.common.MdFileUtils.isMdFile;
import static com.gepardec.sidebarcreator.common.MdFileUtils.isSidebarFile;

public class WikiFileTree {
  private WikiFile wikiFile;
  private WikiFileTree parent;
  private List<WikiFileTree> childrenSorted = new ArrayList<>();

  public WikiFileTree(String folder, List<String> excludedFolders) throws WikiStructureException {
    this(folder, excludedFolders, null);
  }

  private WikiFileTree(String folder, List<String> excludedFolders, WikiFileTree parent) throws WikiStructureException {
    this.parent = parent;

    String folderAbsolute = new File(folder).getAbsolutePath();
    List<String> excludedFoldersAbsolute = excludedFolders.stream().map(f -> new File(f).getAbsolutePath()).collect(Collectors.toList());

    List<File> filesAndDirectoriesInFolder = Arrays.stream(new File(folderAbsolute).listFiles()).sorted(Comparator.comparing(File::getAbsolutePath)).collect(Collectors.toList());

    for(File fileInFolder : filesAndDirectoriesInFolder) {
        if (isMdFile(fileInFolder) && !isSidebarFile(fileInFolder)) {
          if(wikiFile == null) {
            this.wikiFile = new WikiFile(fileInFolder.getAbsolutePath());
          } else {
            throw new WikiStructureException("Multiple md-Files found: " + folder);
          }
        } else if(fileInFolder.isDirectory() && !excludedFoldersAbsolute.contains(fileInFolder.getAbsolutePath()) && !".git".equals(fileInFolder.getName())) {
          childrenSorted.add(new WikiFileTree(fileInFolder.getAbsolutePath(), excludedFolders, this));
        }
    }
  }

  public WikiFile getWikiFile() {
    return wikiFile;
  }

  public WikiFileTree getParent() {
    return parent;
  }

  public List<WikiFileTree> getChildrenSorted() {
    return childrenSorted;
  }

  public boolean isTopLevel() {
    return parent == null;
  }

  public WikiFileTree getTopLevelTree() {
    WikiFileTree result = this;
    while(!result.isTopLevel()) {
      result = result.getParent();
    }
    return result;
  }

  public String getContainingFolder() {
    return this.wikiFile.getParentFolder();
  }

  public List<WikiFile> getAllNodesInTree() {
    List<WikiFile> result = new ArrayList<>();

    if(!this.childrenSorted.isEmpty()) {
      childrenSorted.forEach(tree -> result.addAll(tree.getAllNodesInTree()));
    }

    result.add(this.wikiFile);

    return result;
  }
}
