import commands.Command;
import commands.EchoCommand;
import commands.ExitCommand;

import commands.TypeCommand;
import server.ShellServer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Command> commands = new HashMap<>();
        commands.put("exit", new ExitCommand());
        commands.put("echo", new EchoCommand());

        TypeCommand type = new TypeCommand(commands);
        commands.put("type", type);

        ShellServer.start(commands);
    }
}
