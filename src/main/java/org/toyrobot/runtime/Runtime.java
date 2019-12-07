package org.toyrobot.runtime;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Runtime {
    Stream<String> readFile(Path filePath) throws IOException;

    void print(String text);

    void println();
}
