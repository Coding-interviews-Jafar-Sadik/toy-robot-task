package org.toyrobot.runtime;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Runtime {
    void print(String text);

    Stream<String> readFile(Path filePath) throws IOException;

    void println();
}
