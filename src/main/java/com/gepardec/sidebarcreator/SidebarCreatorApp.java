package com.gepardec.sidebarcreator;

import com.gepardec.sidebarcreator.creator.SidebarCreator;
import com.gepardec.sidebarcreator.parser.ArgumentParser;
import com.gepardec.sidebarcreator.validation.ArgumentValidations;
import com.gepardec.sidebarcreator.validation.WikiStructureValidations;
import com.gepardec.sidebarcreator.validation.exceptions.ArgumentException;
import com.gepardec.sidebarcreator.validation.exceptions.WikiStructureException;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class SidebarCreatorApp {

  public static void main(String[] args) {

    try {
      Arguments arguments = ArgumentParser.parseArguments(args);

      ArgumentValidations.validateArguments(arguments);

      if (arguments.isHelpRequested()) {
        ArgumentParser.showHelp();
      } else {
        WikiStructureValidations.validateWikiStructure(arguments);

        SidebarCreator.of(arguments).createSidebarFiles();
      }
    } catch (ParseException e) {
      System.out.print("Parsing error: ");
      System.out.println(e.getMessage());
      ArgumentParser.showHelp();
    } catch (WikiStructureException e) {
      System.out.print("Wiki structure incorrect: ");
      System.out.println(e.getMessage());
    } catch (ArgumentException e) {
      System.out.print("Invalid arguments: ");
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.print("Error writing file");
      System.out.println(e.getMessage());
    }
  }
}
