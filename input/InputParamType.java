import java.util.function.Function;
import java.util.stream.Stream;

public enum InputParamType {
    INTEGER(Integer.class, "integer", Integer::valueOf),
    LONG(Long.class, "long", Long::valueOf),
    SHORT(Short.class, "short", Short::valueOf),
    FLOAT(Float.class, "float", Float::valueOf),
    DOUBLE(Double.class, "double", Double::valueOf),
    BOOLEAN(Boolean.class, "boolean", Boolean::valueOf),
    STRING(String.class, "string", value -> {
        if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    });

    final Class<?> javaClass;
    final String name;
    final Function<String, Object> parser;

    InputParamType(Class<?> javaClass, String name, Function<String, Object> parser) {
        this.javaClass = javaClass;
        this.name = name;
        this.parser = parser;
    }

    public Object parse(String value) {
        return parser.apply(value);
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public static InputParamType forName(String name) {
        return Stream.of(values())
                .filter(type -> type.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static InputParamType forName(String name, boolean canThrowException) {
        if (!canThrowException) {
            return forName(name);
        }
        return Stream.of(values())
                .filter(type -> type.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown type : " + name));
    }
}