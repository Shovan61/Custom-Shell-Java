package commands;

import parser.ParsedCommand;
import utils.CommandUtils;

import java.util.List;
import java.util.Map;

public class TypeCommand implements Command {
    Map<String, Command> commands;

    public TypeCommand(Map<String, Command> argumentsCommands) {
        this.commands = argumentsCommands;
    }

    @Override
    public void execute(ParsedCommand command) {
        for (String arg : command.args) {
            if (commands.containsKey(arg)) {
                String type = commands.get(arg).type();
                System.out.println(type);
            } else {
                if (!checkPaths(arg)) {
                    System.out.println(arg + ": not found");
                }
            }
        }
    }

    @Override
    public String type() {
        return "type is a shell builtin";
    }

    private static Boolean checkPaths(String arg) {
        List<String> paths = CommandUtils.checkCommandInPaths(arg);

        if (!paths.isEmpty()) {
            for (String location : paths) {
                System.out.println(arg + " is " + location);
            }
            return true;
        } else {
            return false;
        }
    }
}
