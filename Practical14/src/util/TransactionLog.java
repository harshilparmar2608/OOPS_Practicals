package util;

import java.io.IOException;
import java.nio.file.*;

public class TransactionLog {
    private final Path file;

    public TransactionLog(Path file) {
        this.file = file;
    }

    public void log(String line) throws IOException {
        Files.createDirectories(file.getParent());
        Files.writeString(file, line + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
