import java.util.ArrayList;
import java.util.List;

public class InputContext {
    private final String className; // fully qualified class name to be invoked
    private final String methodName; // method name to be invoked
    private final List<InputParameter> parameters; // list of parameters (type and value)

    public InputContext(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = new ArrayList<>();
    }

    public void addParameter(InputParamType type, Object value) {
        parameters.add(new InputParameter(type, value));
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<InputParameter> getParameters() {
        return parameters;
    }

    public List<Object> getParameterValues() {
        return parameters.stream().map(param -> param.value).toList();
    }

    public List<? extends Class<?>> getParameterTypes() {
        return parameters.stream().map(p -> p.type.getJavaClass()).toList();
    }

    @Override
    public String toString() {
        return String.format("InputContext{class=%s, method=%s, params=%s}",
                className, methodName, parameters);
    }
}