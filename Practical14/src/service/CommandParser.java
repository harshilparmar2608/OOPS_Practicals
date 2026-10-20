package service;

public class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid command");
        }
        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        return new Command(type, parts[1], Long.parseLong(parts[2]));
    }
}
