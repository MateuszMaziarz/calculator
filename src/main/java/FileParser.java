import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileParser {
    private static final Set<String> OPERATIONS =
            Arrays.stream(MathOperation.values()).map(Enum::name).collect(Collectors.toCollection(() -> new HashSet<>()));


    public static List<Operation> getOperations(String filePath) {
        List<Operation> listOfParts = new LinkedList<>();

        Path file = Paths.get(filePath);
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(""))
                    addItemToList((LinkedList<Operation>) listOfParts, line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return Collections.unmodifiableList(listOfParts);
    }

    private static void addItemToList(LinkedList<Operation> list, String line) {

        String[] splitLineTab = line.split(" ");

        validate(splitLineTab);
        Operation part = new Operation(MathOperation.valueOf(splitLineTab[0].toUpperCase()),
                splitLineTab[1]);

        if (part.getCommand().equals(MathOperation.APPLY)) {
            list.addFirst(part);
        } else {
            list.addLast(part);
        }
    }

    private static void validate(String[] splitLineTab) throws InputOperationsInvalidException {
        checkLengthOfTab(splitLineTab);
        checkArgumentNotNull(splitLineTab[0], splitLineTab[1]);
        checkCommand(splitLineTab[0]);
    }

    private static void checkLengthOfTab(String[] splitLineTab) throws InputOperationsInvalidException {
        if (splitLineTab.length != 2) {
            throw new InputOperationsInvalidException("Wrong length of argument tab! (this command was skipped)");
        }
    }

    private static void checkArgumentNotNull(String commandInString, String valueInString) throws InputOperationsInvalidException {
        if (commandInString == null) {
            throw new InputOperationsInvalidException("Missing command!");
        }
        if (valueInString == null) {
            throw new InputOperationsInvalidException("Missing a value!");
        }
    }

    private static void checkCommand(String commandInString) throws InputOperationsInvalidException {
        if (!OPERATIONS.contains(commandInString.toUpperCase())) {
            throw new InputOperationsInvalidException("Wrong command");
        }
    }
}
