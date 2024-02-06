package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage){

        // Создание обратной мапы
        Map<String, String> map = storage.toMap();
        Map<String, String> swapMap = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        map.forEach((k, v) -> storage.unset(k));
        swapMap.forEach(storage::set);
    }
}
// END
