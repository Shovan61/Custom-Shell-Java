package server;

import commands.Command;
import parser.ParsedCommand;

import java.util.Map;

public class CommandHandler {
    public static void handle(ParsedCommand parsedCommand, Map<String, Command> commands) {
        System.out.println(parsedCommand.command + ": command not found");
    }
}
