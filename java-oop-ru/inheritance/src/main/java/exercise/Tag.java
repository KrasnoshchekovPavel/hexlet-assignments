package exercise;

import jdk.jshell.spi.ExecutionControl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    private final Map<String, String> attrs;

    private final String name;

    public Tag(String name, Map<String, String> attrs){
        this.name = name;
        this.attrs = new LinkedHashMap<>(attrs);
    }

    public String renderAttrs(){
        return " " + attrs.entrySet().stream()
                .map((e) -> e.getKey() + "=" + "\"" + e.getValue() + "\"")
                .collect(Collectors.joining(" "));
    }

    public String renderOpenTag(){
        return String.format("<%s%s>", name, attrs.isEmpty() ? "" : renderAttrs());
    }

    public String renderCloseTag(){
        return String.format("</%s>", name);
    }

    public String renderTag(String content){
        return String.format("%s%s%s", renderOpenTag(), content, renderCloseTag());
    }
}
// END
