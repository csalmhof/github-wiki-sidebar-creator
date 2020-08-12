package com.gepardec.sidebarcreator.creator;

import com.gepardec.sidebarcreator.Arguments;
import com.gepardec.sidebarcreator.validation.exceptions.WikiStructureException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SidebarCreator {

  private final Arguments arguments;

  private SidebarCreator(Arguments arguments) {
    this.arguments = arguments;
  }

  public static SidebarCreator of(Arguments arguments) {
    return new SidebarCreator(arguments);
  }

  public void createSidebarFiles() throws WikiStructureException, IOException {
    WikiFileTree wikiTree = new WikiFileTree(arguments.getWikiMainFolder(), arguments.getExcludedSubfolders());

    createSidebarFiles(wikiTree);
  }

  private void createSidebarFiles(WikiFileTree tree) throws IOException {
    createSidebarFile(tree);
    for (WikiFileTree childTree : tree.getChildrenSorted()) {
      createSidebarFiles(childTree);
    }
  }

  private void createSidebarFile(WikiFileTree tree) throws IOException {
    deleteSidebarFile(tree);
    File sidebarFileNew = new File(tree.getContainingFolder() + File.separator + "_Sidebar.md");

    FileWriter fileWriter = new FileWriter(sidebarFileNew);
    fileWriter.write(SidebarContentCreator.of(tree).createSidebarContent());
    fileWriter.close();
  }

  private void deleteSidebarFile(WikiFileTree tree) {
    File sidebarFileOld = new File(tree.getContainingFolder() + File.separator + "_Sidebar.md");
    if(sidebarFileOld.exists() && sidebarFileOld.isFile()) {
      sidebarFileOld.delete();
    }
  }
}
