import java.util.List;

public class Calculator {

    public static Double calculateEquation(List<Operation> listOfOperation) throws InputOperationsInvalidException {
        isApplyPresent(listOfOperation);
        checkDivideByZero(listOfOperation);

        return listOfOperation.stream().reduce(0.0,
                (sum, operation) -> calculate(sum, operation),
                (first, second) -> first + second);
    }

    private static double calculate(double currentValue, Operation part) {
        MathOperation command = part.getCommand();
        switch (command) {
            case APPLY:
                return part.getValue();
            case ADD:
                return currentValue + part.getValue();
            case SUBTRACT:
                return currentValue - part.getValue();
            case MULTIPLY:
                return currentValue * part.getValue();
            case DIVIDE:
                return currentValue / part.getValue();
        }
        return 0;
    }

    private static void isApplyPresent(List<Operation> list) throws InputOperationsInvalidException {
        if (!list.isEmpty() && list.get(0).getCommand() != MathOperation.APPLY) {
            throw new InputOperationsInvalidException("Missing APPLY command!");
        }
    }

    private static void checkDivideByZero(List<Operation> listOfOperation) throws InputOperationsInvalidException {
        if(listOfOperation.contains(new Operation(MathOperation.DIVIDE, "0")) ||
                listOfOperation.contains(new Operation(MathOperation.DIVIDE, "0.0"))){
            throw new InputOperationsInvalidException("Attempt to divide by zero! (this command was skipped)");
        }
    }
}
