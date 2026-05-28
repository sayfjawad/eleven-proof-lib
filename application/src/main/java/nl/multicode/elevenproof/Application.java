package nl.multicode.elevenproof;

import java.util.Arrays;
import java.util.List;
import nl.multicode.elevenproof.config.AppConfig;

public class Application {

  public static void main(String[] args) {
    if (args.length == 0 || isHelpRequested(args)) {
      printHelp();
      return;
    }

    final var appConfig = new AppConfig();
    final String command = args[0].toLowerCase();

    try {
      switch (command) {
        case "generate" -> handleGenerate(args, appConfig);
        case "validate" -> handleValidate(args, appConfig);
        default -> {
          System.out.println("Unknown command: " + command);
          printHelp();
        }
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      printHelp();
    }
  }

  private static boolean isHelpRequested(String[] args) {
    List<String> helpOptions = Arrays.asList("--help", "-h", "help");
    return helpOptions.contains(args[0].toLowerCase());
  }

  private static void handleGenerate(String[] args, AppConfig appConfig) {
    if (args.length < 2) {
      System.out.println("Missing type for generate command.");
      printHelp();
      return;
    }
    String type = args[1].toLowerCase();
    switch (type) {
      case "bsn" -> System.out.println(appConfig.getCitizenServiceNumberController().generate().getNumber());
      case "bank" -> System.out.println(appConfig.getBankAccountNumberController().generate().getNumber());
      default -> System.out.println("Unknown type for generate: " + type + ". Supported types: bsn, bank");
    }
  }

  private static void handleValidate(String[] args, AppConfig appConfig) {
    if (args.length < 3) {
      System.out.println("Missing arguments for validate command. Usage: validate <type> <number>");
      printHelp();
      return;
    }
    String type = args[1].toLowerCase();
    String number = args[2];
    boolean isValid = switch (type) {
      case "bsn" -> appConfig.getCitizenServiceNumberController().validate(number).getIsElevenproof();
      case "bank" -> appConfig.getBankAccountNumberController().validate(number).getIsElevenproof();
      case "giro" -> appConfig.getGiroAccountNumberController().validate(number).getIsValid();
      default -> {
        System.out.println("Unknown type for validate: " + type + ". Supported types: bsn, bank, giro");
        yield false;
      }
    };

    if (isValid) {
      System.out.println(number + " is a valid " + type.toUpperCase() + " number.");
    } else {
      System.out.println(number + " is NOT a valid " + type.toUpperCase() + " number.");
    }
  }

  private static void printHelp() {
    System.out.println("""
        ElevenProof CLI Tool
        Usage:
          java -jar application.jar <command> [options]
        
        Commands:
          generate <type>          Generate a valid number.
                                   Types: bsn, bank
                                   
          validate <type> <number> Validate if a given number is valid.
                                   Types: bsn, bank, giro
                                   
          help, --help, -h         Show this help message.
          
        Examples:
          java -jar application.jar generate bsn
          java -jar application.jar validate bsn 123456782
          java -jar application.jar validate giro 1234567
        """);
  }
}
