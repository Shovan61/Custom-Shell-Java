package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
