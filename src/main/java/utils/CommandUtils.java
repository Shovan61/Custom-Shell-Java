package utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandUtils {
    public static List<String> checkCommandInPaths(String arg) {
        List<String> paths = getPaths();
        List<String> commandLocations = new ArrayList<>();

        for (String dir : paths) {
            File executable = new File(dir, arg);
            if (executable.exists() && executable.canExecute()) {
                commandLocations.add(executable.getAbsolutePath());
            }
        }
        return commandLocations;
    }

    public static List<String> getPaths() {
        String path = System.getenv("PATH");
        return Arrays.stream(path.split(":")).toList();
    }

    public static String currentDirectory = System.getProperty("user.dir");

    public static Path resolveToCurrentDir(String filename) {
        Path p = Paths.get(filename);
        if (!p.isAbsolute()) {
            p = Paths.get(currentDirectory).resolve(p).normalize();
        }
        return p;
    }
}
