package commands;

import parser.ParsedCommand;
import utils.CommandUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {
        if (command.args.isEmpty()) {
            // Only cd without arguments
            System.err.println("cd: missing argument");
        } else {
            // cd with arguments
            if (command.args.size() > 1) {
                // cd with more than one argument
                System.err.println("cd: too many arguments");
            } else {
                // cd with one argument
                String restCommand = command.args.getFirst();

                Path searchedDir = Paths.get(restCommand);
                String homeDir = System.getenv("HOME");

                if (searchedDir.toString().equals("~")) {
                    CommandUtils.currentDirectory = homeDir;
                } else {
                    if (!searchedDir.isAbsolute()) {
                        searchedDir = CommandUtils.resolveToCurrentDir(searchedDir.toString());
                    }

                    if (Files.isDirectory(searchedDir)) {
                        CommandUtils.currentDirectory = searchedDir.toString();
                    } else {
                        System.out.println("cd: " + restCommand + ": No such file or directory");
                    }
                }
            }
        }
    }


    @Override
    public String type() {
        return "cd is a shell builtin";
    }
}