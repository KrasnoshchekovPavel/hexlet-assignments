package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator{
    public static List<String> validate(Object object){
        List<String> errorFields = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(!field.isAnnotationPresent(NotNull.class)){
                continue;
            }
            field.setAccessible(true);

            try {
                if(field.get(object) == null){
                    errorFields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return errorFields;
    }
}
// END
