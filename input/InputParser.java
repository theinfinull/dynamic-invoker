import java.io.File;
import java.util.Scanner;

public class InputParser {
    public static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (Exception e) {
            System.out.println("could not open input file: in.txt");
            System.exit(1);
        }
    }

    public static InputContext parseContextFromInputFile() {
        String classAndMethodLine = scanner.nextLine().trim();
        String[] classAndMethodParts = classAndMethodLine.split(" ");
        if (classAndMethodParts.length != 2) {
            throw new RuntimeException("invalid class and method format in in.txt: " + classAndMethodLine);
        }
        InputContext context = new InputContext(classAndMethodParts[0], classAndMethodParts[1]);

        while (scanner.hasNextLine()) {
            String paramLine = scanner.nextLine().trim();
            if (paramLine.equalsIgnoreCase("END")) {
                break;
            }

            String[] paramParts = paramLine.split(":", 2);
            if (paramParts.length != 2) {
                throw new RuntimeException("invalid parameter format in in.txt: " + paramLine);
            }

            String paramTypeStr = paramParts[0];
            String paramValueStr = paramParts[1];

            InputParamType inputParamType = InputParamType.forName(paramTypeStr, true);

            Object value = inputParamType.parse(paramValueStr);
            context.addParameter(inputParamType, value);
        }
        return context;
    }
}
