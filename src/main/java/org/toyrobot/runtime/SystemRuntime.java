package org.toyrobot.runtime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@SuppressWarnings("squid:S106")
public class SystemRuntime implements Runtime {
    @Override
    public Stream<String> readFile(Path filePath) throws IOException {
        return Files.lines(filePath);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println() {
        System.out.println();
    }
}
