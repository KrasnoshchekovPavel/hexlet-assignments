package exercise;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App{
    public static void save(Path path, Car car){
        String text = car.serialize();
        try {
            Files.writeString(path, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Car extract(Path path){

        try {
            return Car.unserialize(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
// END
