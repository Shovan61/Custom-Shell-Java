package commands;

import debug.DebugPrinter;
import parser.ParsedCommand;

public class EchoCommand implements Command{
    @Override
    public void execute(ParsedCommand command) {
        if(command.args.isEmpty()) {
            System.out.println("ECHO is on.");
        } else {
            String result = String.join(" ", command.args);
            System.out.println(result);
        }
    }

    @Override
    public String type() {
        return "echo is a shell builtin";
    }
}
