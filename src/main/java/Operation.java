import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class Operation {
    private final MathOperation command;
    private final Double value;

    public Operation(MathOperation command, String valueInString) {
        checkIsValueANumber(valueInString);

        this.command = command;
        this.value = Double.valueOf(valueInString);
    }

    private static void checkIsValueANumber(String valueInString) {
        if (!NumberUtils.isCreatable(valueInString)) {
            throw new InputOperationsInvalidException("Value is not a number! (this command was skipped)");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return getCommand() == operation.getCommand() &&
                Objects.equals(getValue(), operation.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommand(), getValue());
    }

    public MathOperation getCommand() {
        return command;
    }

    public double getValue() {
        return value;
    }


}