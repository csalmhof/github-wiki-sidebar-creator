package com.gepardec.sidebarcreator.creator;

import com.gepardec.sidebarcreator.common.TestPaths;
import org.junit.Test;

import java.io.File;

import static com.gepardec.sidebarcreator.common.TestPaths.FIRST_SUB_FILE;
import static com.gepardec.sidebarcreator.common.TestPaths.HOME_FILE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WikiFileTest {

  private static final String SUB_FILE_PATH = new File(FIRST_SUB_FILE).getAbsolutePath();
  private static final String HOME_FILE_PATH = new File(HOME_FILE).getAbsolutePath();
  private static final String EXPECTED_SUB_PARENT_FOLDER = new File(TestPaths.FIRST_SUB_FOLDER).getAbsolutePath();
  private static final String EXPECTED_HOME_PARENT_FOLDER = new File(TestPaths.TEST_WIKI).getAbsolutePath();

  @Test
  public void test_standard_construction_sub_file() {
    WikiFile wikiFile = new WikiFile(FIRST_SUB_FILE);

    assertThat(wikiFile.getAsFile().getAbsolutePath(), is(SUB_FILE_PATH));
    assertThat(wikiFile.getParentFolder(), is(EXPECTED_SUB_PARENT_FOLDER));
    assertThat(wikiFile.getDisplayName(), is("First Sub Part"));
    assertThat(wikiFile.getFileName(), is("01_First-Sub-Part"));
    assertThat(wikiFile.getFilePath(), is(SUB_FILE_PATH));
  }

  @Test
  public void test_file_constructor_sub_file() {
    WikiFile wikiFile = new WikiFile(new File(SUB_FILE_PATH));

    assertThat(wikiFile.getAsFile().getAbsolutePath(), is(SUB_FILE_PATH));
    assertThat(wikiFile.getParentFolder(), is(EXPECTED_SUB_PARENT_FOLDER));
    assertThat(wikiFile.getDisplayName(), is("First Sub Part"));
    assertThat(wikiFile.getFileName(), is("01_First-Sub-Part"));
    assertThat(wikiFile.getFilePath(), is(SUB_FILE_PATH));
  }

  @Test
  public void test_standard_construction_home_file() {
    WikiFile wikiFile = new WikiFile(HOME_FILE);

    assertThat(wikiFile.getAsFile().getAbsolutePath(), is(HOME_FILE_PATH));
    assertThat(wikiFile.getParentFolder(), is(EXPECTED_HOME_PARENT_FOLDER));
    assertThat(wikiFile.getDisplayName(), is("Home"));
    assertThat(wikiFile.getFileName(), is("Home"));
    assertThat(wikiFile.getFilePath(), is(HOME_FILE_PATH));
  }

  @Test
  public void test_file_constructor_home_file() {
    WikiFile wikiFile = new WikiFile(new File(HOME_FILE));

    assertThat(wikiFile.getAsFile().getAbsolutePath(), is(HOME_FILE_PATH));
    assertThat(wikiFile.getParentFolder(), is(EXPECTED_HOME_PARENT_FOLDER));
    assertThat(wikiFile.getDisplayName(), is("Home"));
    assertThat(wikiFile.getFileName(), is("Home"));
    assertThat(wikiFile.getFilePath(), is(HOME_FILE_PATH));
  }

}
