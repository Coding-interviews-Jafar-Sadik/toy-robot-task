package org.toyrobot.test;

import org.assertj.core.util.Streams;
import org.toyrobot.runtime.Runtime;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class FakeRuntime implements Runtime {
    private final Path fakeFilePath;
    private final List<String> fakeFileContent;
    private List<String> consoleOutput = new LinkedList<>();
    private String currentLine = "";

    public FakeRuntime(Path fakeFilePath, List<String> fakeFileContent) {
        this.fakeFilePath = fakeFilePath;
        this.fakeFileContent = fakeFileContent;
    }

    @Override
    public Stream<String> readFile(Path filePath) throws IOException {
        if (filePath.equals(this.fakeFilePath)) {
            return Streams.stream(fakeFileContent);
        }
        throw new NoSuchFileException(filePath.toString());
    }

    @Override
    public void print(String text) {
        currentLine += text;
    }

    @Override
    public void println() {
        consoleOutput.add(currentLine);
        currentLine = "";
    }

    public List<String> getConsoleOutput() {
        return consoleOutput;
    }
}
