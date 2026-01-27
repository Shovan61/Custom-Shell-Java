package server;

import commands.Command;
import parser.ParsedCommand;
import utils.CommandUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CommandHandler {
    public static void handle(ParsedCommand parsedCommand, Map<String, Command> commands) {
        if (commands.containsKey(parsedCommand.command)) {
            commands.get(parsedCommand.command).execute(parsedCommand);
        } else {
            List<String> commandLocations = CommandUtils.checkCommandInPaths(parsedCommand.command);
            if (commandLocations.isEmpty()) {
                System.out.println(parsedCommand.command + ": command not found");
            } else {
                try {
                    int statusCode = runExecutable(parsedCommand, commandLocations);
                    if (statusCode != 0) {
                        System.out.printf(parsedCommand.command + ": command failed");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //This Method is to run a program
    private static int runExecutable(ParsedCommand parsedCommand, List<String> commandLocations) throws IOException, InterruptedException {
        if (commandLocations.size() > 1) {
            System.out.println("Multiple executable found!");
        }

        String executablePath = commandLocations.getFirst();
        File executableFile = new File(executablePath);

        if (!executableFile.exists() && !executableFile.canExecute()) {
            System.out.println(parsedCommand + "can not be execute");
            return 1;
        }

        String processName = executableFile.getName();
        ProcessBuilder processBuilder = new ProcessBuilder(processName);

        processBuilder.command().addAll(parsedCommand.args);

        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = processBuilder.start();
        return process.waitFor();
    }
}
