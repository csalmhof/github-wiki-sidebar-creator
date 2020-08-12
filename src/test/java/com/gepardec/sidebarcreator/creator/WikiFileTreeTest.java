package com.gepardec.sidebarcreator.creator;

import com.gepardec.sidebarcreator.validation.exceptions.WikiStructureException;
import org.junit.Test;

import java.io.File;

import static com.gepardec.sidebarcreator.common.TestPaths.EXCLUDED_FOLDERS_ALL;
import static com.gepardec.sidebarcreator.common.TestPaths.TEST_WIKI;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WikiFileTreeTest {

  @Test
  public void fromTestWiki() throws WikiStructureException {
    WikiFileTree wikiFileTree = new WikiFileTree(TEST_WIKI, EXCLUDED_FOLDERS_ALL);

    assertThat(wikiFileTree.getWikiFile().getDisplayName(), is("Home"));
    assertNull(wikiFileTree.getParent());
    assertTrue(wikiFileTree.isTopLevel());
    assertThat(wikiFileTree.getContainingFolder(), is(new File(TEST_WIKI).getAbsolutePath()));
    assertThat(wikiFileTree.getChildrenSorted().size(), is(2));
    assertThat(wikiFileTree.getAllNodesInTree().size(), is(6));
  }
}
