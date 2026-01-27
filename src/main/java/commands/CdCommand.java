package commands;

import debug.DebugPrinter;
import parser.ParsedCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {

        if (command.args.isEmpty()) {
//            Only cd without arguments
            System.err.println("cd: missing argument");
        } else {
//            cd with arguments
            if (command.args.size() > 1) {
//                cd with more than one argument
                System.out.println(command.command + ": too many arguments");
            } else {
//                cd with one argument
                String path = command.args.getFirst();
                changeDirectory(path);
            }
        }
    }

    private static void changeDirectory(String path) {
//        check if it is an absolute path
        if (path.startsWith("/")) {
            Path newPath = Paths.get(path);

            // Check if directory exists and is accessible
            if (Files.exists(newPath)) {
                if (Files.isDirectory(newPath)) {
                    if (Files.isReadable(newPath)) {
                        // Update current directory
                        try {
                            System.setProperty("user.dir", newPath.toRealPath().toString());
                        } catch (IOException e) {
                            System.setProperty("user.dir", newPath.toAbsolutePath().toString());

                        }
                    }
                    else {
                        System.err.println("cd: " + path + ": Permission denied");
                    }
                }
                else {
                    System.err.println("cd: " + path + ": Not a directory");
                }
            }
            else {
                System.err.println("cd: " + path + ": No such file or directory");

            }
        }
    }

    @Override
    public String type() {
        return "cd is a shell builtin";
    }
}
