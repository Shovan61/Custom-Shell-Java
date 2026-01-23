package commands;

import debug.DebugPrinter;
import parser.ParsedCommand;

public class EchoCommand implements Command{
    @Override
    public void execute(ParsedCommand command) {
        System.out.println(DebugPrinter.toJson(command));
    }
}
