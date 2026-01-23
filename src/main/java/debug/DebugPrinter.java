package debug;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class DebugPrinter {

    public static String toJson(Object obj) {
        if (obj == null) return "null";

        Class<?> cls = obj.getClass();

        // primitives + wrappers + String
        if (cls.isPrimitive() ||
                obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character) {
            return "\"" + obj + "\"";
        }

        // arrays
        if (cls.isArray()) {
            StringBuilder sb = new StringBuilder("[");
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                sb.append(toJson(Array.get(obj, i)));
                if (i < len - 1) sb.append(", ");
            }
            return sb.append("]").toString();
        }

        // collections (List, Set)
        if (obj instanceof Collection<?>) {
            StringBuilder sb = new StringBuilder("[");
            var it = ((Collection<?>) obj).iterator();
            while (it.hasNext()) {
                sb.append(toJson(it.next()));
                if (it.hasNext()) sb.append(", ");
            }
            return sb.append("]").toString();
        }

        // maps
        if (obj instanceof Map<?, ?> map) {
            StringBuilder sb = new StringBuilder("{");
            var it = map.entrySet().iterator();
            while (it.hasNext()) {
                var e = it.next();
                sb.append("\"").append(e.getKey()).append("\": ")
                        .append(toJson(e.getValue()));
                if (it.hasNext()) sb.append(", ");
            }
            return sb.append("}").toString();
        }

        // objects
        StringBuilder sb = new StringBuilder("{");
        Field[] fields = cls.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                sb.append("\"")
                        .append(fields[i].getName())
                        .append("\": ")
                        .append(toJson(fields[i].get(obj)));
            } catch (IllegalAccessException ignored) {}

            if (i < fields.length - 1) sb.append(", ");
        }

        return sb.append("}").toString();
    }
}

