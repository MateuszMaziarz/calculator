import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class FileParserTest {

    @Test
    public void addingTwoToTenWillGiveTwelve() {
        List<Operation> operations = FileParser.getOperations("src/test/java/resources/addingTwoToTenWillGiveTwelve.txt");

        assertThat(operations)
                .containsExactly(
                        new Operation(MathOperation.APPLY, "10"),
                        new Operation(MathOperation.ADD, "2")
                );
    }

    @Test
    public void emptyFile() {
        List<Operation> operations = FileParser.getOperations("src/test/java/resources/emptyFile.txt");
        assertThat(operations).isEmpty();
    }

    @Test
    public void fileWithEmptyLinesBetweenAddingTwoToTenWillGiveTwelve() {
        List<Operation> operations = FileParser.getOperations("src/test/java/resources/fileWithEmptyLines.txt");

        assertThat(operations)
                .containsExactly(
                        new Operation(MathOperation.APPLY, "10"),
                        new Operation(MathOperation.ADD, "2")
                );
    }

    @Test
    public void fileWithWrongValue() {
        assertThatThrownBy(() -> FileParser.getOperations("src/test/java/resources/fileWithWrongValue.txt"))
                .isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Value is not a number! (this command was skipped)");
    }

    @Test
    public void fileWithWrongCommand() {
        assertThatThrownBy(() -> FileParser.getOperations("src/test/java/resources/fileWithWrongCommand.txt"))
                .isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Wrong command");
    }

    @Test
    public void fileWithToManyArguments() {
        assertThatThrownBy(() -> FileParser.getOperations("src/test/java/resources/fileWithToManyArguments.txt"))
                .isInstanceOf(InputOperationsInvalidException.class)
                .hasMessageContaining("Wrong length of argument tab! (this command was skipped)");
    }

}