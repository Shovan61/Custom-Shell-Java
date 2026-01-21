import commands.Command;
import server.ShellServer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Command> commands = new HashMap<>();
        ShellServer.start(commands);
    }
}
