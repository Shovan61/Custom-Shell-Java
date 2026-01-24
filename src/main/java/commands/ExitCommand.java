package commands;

import parser.ParsedCommand;

import java.util.List;

public class ExitCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {
        StatusReport statusReport = checkArgs(command.args);
        if (statusReport.success) {
            System.exit(statusReport.exitCode);
        }
    }

    @Override
    public String type() {
        return "exit is a shell builtin";
    }

    private StatusReport checkArgs(List<String> args) {
        StatusReport statusReport = new StatusReport();
        statusReport.success = false;
        statusReport.exitCode = 1;

        if (args.isEmpty()) {
            statusReport.exitCode = 0;
            statusReport.success = true;
            return statusReport;
        } else if (args.size() > 1) {

            System.out.println(("exit: too many arguments"));
            return statusReport;
        } else {
            statusReport.success = true;
            String arg = args.getFirst();
            try {
                statusReport.exitCode = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("exit: " + arg + " numeric argument required");
            }
        }

        return statusReport;
    }

    static class StatusReport {
        public Boolean success;
        public int exitCode;
    }
}
