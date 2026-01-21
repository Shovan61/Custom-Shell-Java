package parser;

import java.util.List;

public class ParsedCommand {
    public String command;
    public List<String> args;

    public static ParsedCommand fromInput(String command) {
        ParsedCommand res = new ParsedCommand();
        res.command = command;
        return res;
    }
}
