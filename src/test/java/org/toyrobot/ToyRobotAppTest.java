package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.FakeRuntime;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ToyRobotAppTest {
    private final Path scriptFilePath = Paths.get("scripts/fake-script.txt");
    private final Path unknownScriptFilePath = Paths.get("scripts/unknown.txt");

    @Test
    public void applicationShouldProcessAllRobotCommands() throws IOException {
        var runtime = new FakeRuntime(scriptFilePath, List.of(
                "place 3,2,south",
                "right",
                "move",
                "report"
        ));
        new ToyRobotApp(runtime).executeRobotCommands(scriptFilePath);
        assertThat(runtime.getConsoleOutput()).containsExactly(
                "place 3,2,south",
                "right",
                "move",
                "report => Output: 2,2,WEST"
        );
    }

    @Test
    public void applicationProducesNoOutputWhenCommandFileIsEmpty() throws IOException {
        var runtime = new FakeRuntime(scriptFilePath, List.of());
        new ToyRobotApp(runtime).executeRobotCommands(scriptFilePath);
        assertThat(runtime.getConsoleOutput()).isEmpty();
    }

    @Test(expected = IOException.class)
    public void applicationFailsWhenFileDoesNotExist() throws IOException {
        var runtime = new FakeRuntime(scriptFilePath, List.of());
        new ToyRobotApp(runtime).executeRobotCommands(unknownScriptFilePath);
    }
}
