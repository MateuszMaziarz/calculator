import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


class CalculatorTest {

    @Test
    public void addingTwoToTenWillGiveTwelve() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "10"));
        operationList.add(new Operation(MathOperation.ADD, "2"));

        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(12.0);
    }

    @Test
    public void subtractingTwoFromTenWillGiveEight() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "10"));
        operationList.add(new Operation(MathOperation.SUBTRACT, "2"));

        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(8.0);
    }

    @Test
    public void multiplyTenByThreeWillGiveTwenty() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "10"));
        operationList.add(new Operation(MathOperation.MULTIPLY, "3"));

        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(30.0);
    }

    @Test
    public void divideTenByTwoWillGiveFive() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "10"));
        operationList.add(new Operation(MathOperation.DIVIDE, "2"));

        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(5.0);
    }


    @Test
    public void applyOperationInTheMiddleOfList() {
    List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.ADD, "3"));
        operationList.add(new Operation(MathOperation.APPLY, "10"));
        operationList.add(new Operation(MathOperation.DIVIDE, "2"));
        assertThatThrownBy(() ->  Calculator.calculateEquation(operationList) ).isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Missing APPLY command!");
    }

    @Test
    public void calculateEquationWithOneElement() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "10"));

        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(10.0);
    }

    @Test
    public void emptyOperationsListGivesZero() {
        List<Operation> operationList = new LinkedList<>();
        assertThat(Calculator.calculateEquation(operationList)).isEqualTo(0.0);
    }

    @Test
    public void missingApplyWillThrowInputOperationsInvalidException() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.ADD, "2"));
        operationList.add(new Operation(MathOperation.MULTIPLY, "3"));

        assertThatThrownBy(() ->  Calculator.calculateEquation(operationList) ).isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Missing APPLY command!");
    }

    @Test
    public void dividingByZero() {
        List<Operation> operationList = new LinkedList<>();
        operationList.add(new Operation(MathOperation.APPLY, "2"));
        operationList.add(new Operation(MathOperation.DIVIDE, "0"));

        assertThatThrownBy(() ->  Calculator.calculateEquation(operationList) ).isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Attempt to divide by zero! (this command was skipped)");
    }

}