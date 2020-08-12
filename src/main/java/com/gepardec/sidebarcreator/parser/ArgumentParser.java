package com.gepardec.sidebarcreator.parser;

import com.gepardec.sidebarcreator.Arguments;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ArgumentParser {

  public static final String HELP_OPTION_SHORT = "h";
  public static final String HELP_OPTION_LONG = "help";
  public static final String HELP_OPTION_DESCRIPTION = "Show this help message";

  public static final String PATH_OPTION_SHORT = "p";
  public static final String PATH_OPTION_LONG = "path";
  public static final String PATH_OPTION_DESCRIPTION = "The main directory of the GitHub Wiki";

  public static final String EXCLUDE_OPTION_SHORT = "e";
  public static final String EXCLUDE_OPTION_LONG = "exclude";
  public static final String EXCLUDE_OPTION_DESCRIPTION = "Subfolders where no sidebar should be created";


  public static Arguments parseArguments(String[] args) throws ParseException {
    Options options = createOptions();

    return parseOptions(args, options);
  }

  public static void showHelp() {
    Options options = createOptions();
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("SidebarCreator.jar", options);
  }

  private static Options createOptions() {
    Options options = new Options();

    Option helpOption = Option.builder(HELP_OPTION_SHORT)
        .desc(HELP_OPTION_DESCRIPTION)
        .longOpt(HELP_OPTION_LONG)
        .numberOfArgs(0)
        .build();
    Option directoryOption = Option.builder(PATH_OPTION_SHORT)
        .desc(PATH_OPTION_DESCRIPTION)
        .longOpt(PATH_OPTION_LONG)
        .optionalArg(false)
        .numberOfArgs(1)
        .build();

    Option excludeOption = Option.builder(EXCLUDE_OPTION_SHORT)
        .desc(EXCLUDE_OPTION_DESCRIPTION)
        .longOpt(EXCLUDE_OPTION_LONG)
        .numberOfArgs(Option.UNLIMITED_VALUES)
        .optionalArg(false)
        .build();

    options.addOption(helpOption);
    options.addOption(directoryOption);
    options.addOption(excludeOption);
    return options;
  }

  private static Arguments parseOptions(String[] args, Options options) throws ParseException {
    CommandLine commandLine;
    CommandLineParser parser = new DefaultParser();
    Arguments arguments = new Arguments();

    commandLine = parser.parse(options, args);

    arguments.setHelpRequested(parseHelpOption(commandLine));
    arguments.setWikiMainFolder(parsePathOption(commandLine));
    arguments.setExcludedSubfolders(parseExcludedSubfoldersOptionWhenMainFolderOptionIsSet(commandLine));

    return arguments;
  }

  private static boolean parseHelpOption(CommandLine commandLine) {
    return commandLine.hasOption(HELP_OPTION_SHORT);
  }

  private static String parsePathOption(CommandLine commandLine) {
    if (commandLine.hasOption(PATH_OPTION_SHORT)) {
      File mainFolder = new File(commandLine.getOptionValue(PATH_OPTION_SHORT));
      return mainFolder.getAbsolutePath();
    }
    return null;
  }

  private static List<String> parseExcludedSubfoldersOptionWhenMainFolderOptionIsSet(CommandLine commandLine) {
    if(commandLine.hasOption(PATH_OPTION_SHORT) &&  commandLine.hasOption(EXCLUDE_OPTION_SHORT)) {
      File mainFolder = new File(commandLine.getOptionValue(PATH_OPTION_SHORT));
      return Arrays.stream(commandLine.getOptionValues(EXCLUDE_OPTION_SHORT)).map(e -> new File(mainFolder + File.separator + e).getAbsolutePath()).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
