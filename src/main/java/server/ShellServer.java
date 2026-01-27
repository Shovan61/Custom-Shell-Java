package server;

import commands.Command;
import parser.ParsedCommand;

import java.util.Map;
import java.util.Scanner;

public class ShellServer {
    public static void start(Map<String, Command> commands) {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();
            ParsedCommand parsedCommand = ParsedCommand.fromInput(command);

            CommandHandler.handle(parsedCommand, commands);
        }
    }
}
