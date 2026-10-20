package util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FilesReport {
    public static String generate(Path folder) throws IOException {
        if (!Files.exists(folder)) {
            return "Total deposits: 0" + System.lineSeparator()
                    + "Total withdrawals: 0" + System.lineSeparator()
                    + "Net change: 0";
        }
        long deposits = 0;
        long withdrawals = 0;

        try (Stream<Path> paths = Files.walk(folder)) {
            for (Path path : paths.filter(Files::isRegularFile).toList()) {
                for (String line : Files.readAllLines(path)) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 3) {
                        long amount = Long.parseLong(parts[2]);
                        if (parts[0].equals("DEPOSIT")) {
                            deposits += amount;
                        } else if (parts[0].equals("WITHDRAW")) {
                            withdrawals += amount;
                        }
                    }
                }
            }
        }

        return "Total deposits: " + deposits + System.lineSeparator()
                + "Total withdrawals: " + withdrawals + System.lineSeparator()
                + "Net change: " + (deposits - withdrawals);
    }
}
