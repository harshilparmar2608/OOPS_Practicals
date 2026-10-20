package util;

import model.Account;
import java.io.*;
import java.nio.file.Path;

public class StatePersister {
    public static void save(Account[] accounts, Path file) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            output.writeObject(accounts);
        }
    }

    public static Account[] load(Path file) throws IOException, ClassNotFoundException {
        if (!file.toFile().exists()) {
            return new Account[0];
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            return (Account[]) input.readObject();
        }
    }
}
