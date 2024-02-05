package exercise;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n){
        Collections.sort(homes);
        return homes.stream().limit(n).map(String::valueOf).toList();
    }
}
// END
