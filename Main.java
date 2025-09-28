import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        InputContext context;
        try {
            context = InputParser.parseContextFromInputFile();
        } catch (Exception e) {
            handleParseContextException(e);
            return;
        }
        invokeMethod(context);
    }

    private static void invokeMethod(InputContext context) {
        System.out.println("LOG: invoking " + context);

        Object result = null;
        try {
            Class<?> targetClass = Class.forName(context.getClassName());
            Method targetMethod = targetClass.getMethod(
                    context.getMethodName(),
                    context.getParameterTypes().toArray(Class[]::new));
            result = targetMethod.invoke(targetClass, context.getParameterValues().toArray());
        } catch (Exception e) {
            handleInvokeMethodException(e, context);
        }

        System.out.println("LOG: result: " + result);
    }

    private static void handleParseContextException(Exception e) {
        String errorMessage = "internal error";

        if (e instanceof RuntimeException) {
            errorMessage = e.getMessage();
        }

        if (AppProperties.ENABLE_DETAILED_LOGGING) {
            throw new RuntimeException("ERROR: " + errorMessage, e);
        } else {
            System.err.println("ERROR: " + errorMessage);
        }
    }

    private static void handleInvokeMethodException(Exception e, InputContext context) {
        String errorMessage = "internal error";

        if (e instanceof ClassNotFoundException) {
            errorMessage = "class not found: " + context.getClassName();
        } else if (e instanceof NoSuchMethodException) {
            errorMessage = "method not found: " + context.getMethodName() +
                    " with parameter types " + context.getParameterTypes();
        } else if (e instanceof IllegalArgumentException) {
            errorMessage = "illegal argument(s) for method: " + context;
        }

        if (AppProperties.ENABLE_DETAILED_LOGGING) {
            throw new RuntimeException("ERROR: " + errorMessage, e);
        } else {
            System.err.println("ERROR: " + errorMessage);
        }
    }
}