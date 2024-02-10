package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String content;
    private List<Tag> children;
    public PairedTag(String name, Map<String, String> attrs, String content, List<Tag> children) {
        super(name, attrs);
        this.content =  content;
        this.children = children;
    }

    @Override
    public String toString() {

        String childrenText = children.stream().map(Tag::renderOpenTag).collect(Collectors.joining());
        return renderTag(content + childrenText);
    }
}
// END
