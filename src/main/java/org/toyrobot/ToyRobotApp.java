package org.toyrobot;

import org.toyrobot.runtime.Runtime;
import org.toyrobot.runtime.SystemRuntime;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToyRobotApp {
    private final ToyRobot toyRobot;
    private final Runtime runtime;

    public ToyRobotApp(Runtime runtime) {
        this.toyRobot = new ToyRobot(runtime);
        this.runtime = runtime;
    }

    public static void main(String[] args) throws IOException {
        final ToyRobotApp app = new ToyRobotApp(new SystemRuntime());
        Path commandFilePath = Paths.get(args[0]);
        app.executeRobotCommands(commandFilePath);
    }

    private void executeRobotCommands(Path filePath) throws IOException {
        runtime.readFile(filePath).forEach(command -> {
            runtime.print(command + " ");
            toyRobot.execute(command);
            runtime.println();
        });
    }
}
