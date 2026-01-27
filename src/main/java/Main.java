import commands.*;

import server.ShellServer;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Command> commands = new HashMap<>();
        commands.put("exit", new ExitCommand());
        commands.put("echo", new EchoCommand());

        TypeCommand type = new TypeCommand(commands);
        commands.put("type", type);

        commands.put("pwd", new PwdCommand());

        ShellServer.start(commands);
    }
}
