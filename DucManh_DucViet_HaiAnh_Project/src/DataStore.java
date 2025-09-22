import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataStore<T extends Serializable> {
    private final Path file;
    private final Class<T> type;

    public DataStore(Path file, Class<T> type) {
        this.file = file;
        this.type = type;
    }

    public List<T> load() {
        if (file == null || Files.notExists(file)) {
            return new ArrayList<>();
        }
        try {
            if (Files.size(file) == 0) {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to access data file " + file, e);
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {
            Object data = in.readObject();
            if (data instanceof List<?>) {
                List<?> raw = (List<?>) data;
                List<T> typed = new ArrayList<>(raw.size());
                for (Object element : raw) {
                    typed.add(type.cast(element));
                }
                return typed;
            }
            return new ArrayList<>();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read data from " + file, e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Serialized data contains unknown types", e);
        }
    }

    public void save(List<T> data) {
        if (file == null) {
            return;
        }
        try {
            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {
                out.writeObject(new ArrayList<>(data));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to save data to " + file, e);
        }
    }
}
