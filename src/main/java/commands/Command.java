package commands;

import parser.ParsedCommand;

public interface Command {
    void execute(ParsedCommand command);
}
