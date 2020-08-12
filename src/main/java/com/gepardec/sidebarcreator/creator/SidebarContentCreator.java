package com.gepardec.sidebarcreator.creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SidebarContentCreator {

  private WikiFileTree fileTree;

  private SidebarContentCreator(WikiFileTree fileTree) {
    this.fileTree = fileTree;
  }

  public static SidebarContentCreator of(WikiFileTree fileTree) {
    return new SidebarContentCreator(fileTree);
  }

  public String createSidebarContent() {
    String contentBuilder = createSidebarHeader() +
        "\n" +
        createSidebarBody();
    return contentBuilder;
  }

  private String createSidebarBody() {
    WikiFileTree topLevelTree = fileTree.getTopLevelTree();

    return getTreeBodyContent(topLevelTree, 0);
    
  }

  private String getTreeBodyContent(WikiFileTree tree, int prefixlevel){
    int numPrefix = 1;
    StringBuilder treeBodyBuilder = new StringBuilder();

    for(WikiFileTree childTree : tree.getChildrenSorted()) {
      if(prefixlevel == 0) {
        treeBodyBuilder.append(numPrefix).append(". ");
      } else {
        for(int i = 0; i < prefixlevel; i++) {
          treeBodyBuilder.append("\t");
        }
        treeBodyBuilder.append("* ");
      }

      if(childTree.getWikiFile() == this.fileTree.getWikiFile()) {
        treeBodyBuilder.append("**");
      }

      treeBodyBuilder.append("[").append(childTree.getWikiFile().getDisplayName()).append("]");
      treeBodyBuilder.append("(").append(childTree.getWikiFile().getFileName()).append(")");

      if(childTree.getWikiFile() == this.fileTree.getWikiFile()) {
        treeBodyBuilder.append("**");
      }

      treeBodyBuilder.append("\n");

      if(childTree.getAllNodesInTree().contains(this.fileTree.getWikiFile())) {
        treeBodyBuilder.append(getTreeBodyContent(childTree, prefixlevel+1));
      }
    }

    return treeBodyBuilder.toString();
  }
  

  private String createSidebarHeader() {
    StringBuilder headerBuilder = new StringBuilder();

    headerBuilder.insert(0, "**[Home](./)");

    for (WikiFile file : getFileNamesToHeader(fileTree)) {
      headerBuilder.append(" > ");
      headerBuilder.append("[").append(file.getDisplayName()).append("]");
      headerBuilder.append("(").append(file.getFileName()).append(")");
    }

    headerBuilder.append("**");
    headerBuilder.append(System.lineSeparator());

    return headerBuilder.toString();

  }

  private List<WikiFile> getFileNamesToHeader(WikiFileTree fileTree) {
    List<WikiFile> result = new ArrayList<>();
    if(fileTree.isTopLevel()) {
      return Collections.emptyList();
    }
    if (!fileTree.getParent().isTopLevel()) {
      result.addAll(getFileNamesToHeader(fileTree.getParent()));
    }
    result.add(fileTree.getWikiFile());
    return result;
  }

}
