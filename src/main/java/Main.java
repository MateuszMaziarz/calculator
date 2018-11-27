public class Main {
    public static void main(String args[]){
       Double result = Calculator.calculateEquation(FileParser.getOperations(args[0]));
       System.out.print(result);
    }
}
