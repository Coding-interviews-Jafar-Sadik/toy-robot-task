package org.toyrobot;

import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.commands.CommandParser;
import org.toyrobot.runtime.Runtime;
import org.toyrobot.runtime.SystemRuntime;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToyRobotApp {
    private final ToyRobot toyRobot = new ToyRobot();
    private final CommandParser commandParser = new CommandParser();
    private final Runtime runtime;

    public ToyRobotApp(Runtime runtime) {
        this.runtime = runtime;
    }

    public static void main(String[] args) throws IOException {
        var application = new ToyRobotApp(new SystemRuntime());
        application.executeRobotCommands(Paths.get(args[0]));
    }

    public void executeRobotCommands(Path filePath) throws IOException {
        runtime.readFile(filePath).forEach(commandText -> {
            AbstractCommand command = commandParser.parse(commandText);
            runtime.print(commandText);
            toyRobot.execute(command).ifPresent(
                    report -> runtime.print(" => " + report)
            );
            runtime.println();
        });
    }
}
