package commands;

import parser.ParsedCommand;
import utils.CommandUtils;

public class PwdCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {
        String currentDirectory = CommandUtils.currentDirectory;
        System.out.println(currentDirectory);
    }

    @Override
    public String type() {
        return "pwd is a shell builtin";
    }
}
