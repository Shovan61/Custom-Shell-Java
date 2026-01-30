package parser;

import debug.DebugPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ParsedCommand {
    public String command;
    public List<String> args;

    public ParsedCommand(String command, List<String> args) {
        this.command = command;
        this.args = args;
    }

    public ParsedCommand() {
    }

    public ParsedCommand(String command) {
        this.command = command;
        this.args = new ArrayList<>();
    }

    public static ParsedCommand fromInput(String line) {

        List<String> arguments = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inDoubleQuotes = false;
        boolean inSingleQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '\'' && !inDoubleQuotes) {
                inSingleQuotes = !inSingleQuotes; // toggle single quotes
                continue;
            }

            if (ch == '"' && !inSingleQuotes) {
                inDoubleQuotes = !inDoubleQuotes; // toggle double quotes
                continue;
            }

            if (ch == ' ' && !inSingleQuotes && !inDoubleQuotes) {
                if (!current.isEmpty()) {
                    arguments.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(ch);
            }

        }

        if (!current.isEmpty()) {
            arguments.add(current.toString());
        }

        ParsedCommand parsedCommand = new ParsedCommand(arguments.getFirst());
        parsedCommand.args = arguments.subList(1, arguments.size());

        return parsedCommand;


    }
}
