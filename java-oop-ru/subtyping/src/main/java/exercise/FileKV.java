package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{

    private final Path filePath;

    public FileKV(String fileName, Map<String, String> map) {

        filePath = Path.of(fileName);
        write(map);

    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = read();
        map.put(key, value);
        write(map);
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = read();
        map.remove(key);
        write(map);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = read();
        String value = map.get(key);
        return value == null ? defaultValue : value;
    }

    @Override
    public Map<String, String> toMap() {
        return read();
    }

    private void write(Map<String, String> map) {

        String json = Utils.serialize(map);
        try {
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String, String> read() {

        String json = null;
        try {
            json = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Utils.unserialize(json);
    }
}
// END
