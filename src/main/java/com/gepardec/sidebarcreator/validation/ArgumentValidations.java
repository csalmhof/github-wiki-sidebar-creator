package com.gepardec.sidebarcreator.validation;

import com.gepardec.sidebarcreator.Arguments;
import com.gepardec.sidebarcreator.validation.exceptions.ArgumentException;

import java.io.File;

public abstract class ArgumentValidations {

  public static void validateArguments(Arguments arguments) throws ArgumentException {
    if (arguments.isHelpRequested()) {
      return;
    }

    validatePathRequiredWhenHelpOptionIsNotUsed(arguments);
    validateMainFolder(arguments);
    validateExcludedSubdirectories(arguments);
  }

  private static void validatePathRequiredWhenHelpOptionIsNotUsed(Arguments arguments) throws ArgumentException {
    if (!arguments.isHelpRequested() && arguments.getWikiMainFolder() == null) {
      throw new ArgumentException("Missing required option: p");
    }
  }

  private static void validateMainFolder(Arguments arguments) throws ArgumentException {
    validateIsFolder(arguments.getWikiMainFolder());
  }

  private static void validateExcludedSubdirectories(Arguments arguments) throws ArgumentException {
    for (String subfolderPath : arguments.getExcludedSubfolders()) {
      validateIsFolder(subfolderPath);
    }
  }


  private static void validateIsFolder(String folderPath) throws ArgumentException {
    File folder = new File(folderPath);
    if (!folder.exists()) {
      throw new ArgumentException(folder.getAbsolutePath() + " does not exist");
    }

    if (!folder.isDirectory()) {
      throw new ArgumentException(folder.getAbsolutePath() + " is no directory");
    }
  }
}
