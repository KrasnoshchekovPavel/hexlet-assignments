package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class SingleTag extends Tag{

    public SingleTag(String name, Map<String, String> attrs) {
        super(name, attrs);
    }

    @Override
    public String toString() {
        return renderOpenTag();
    }
}
// END
