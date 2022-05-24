import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;

public class Calculator {
  HashMap<String, Variable> Variables = new HashMap<String, Variable>();
  Scanner input = new Scanner(System.in);
  
  public Calculator() {
    Variables = new HashMap<String, Variable>();
    input = new Scanner(System.in);
  }

  private void run() {
    Display.clear();
    boolean running = true;
    while(running) {
      System.out.println("Command:");
      String term = input.nextLine();
      Display.clear();
      route(term);
    }
  }
  
  private void route(String term) {
    switch (term) {
      case "new variable":
        newVariable();
        break;
      
      case "print variable":
        printVariable();
        break;
    }
  }

  private void newVariable() {
    int intValue;
    double doubleValue;
    String name;
    System.out.println("Type:");
    String type = input.nextLine();
    Display.clear();
    switch (type) {
      case "Integer":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        intValue = input.nextInt();
        Display.clear();
        Variables.put(name, new Int(intValue, name));
        break;
      case "Float":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        doubleValue = input.nextDouble();
        Display.clear();
        Variables.put(name, new Float(doubleValue, name));
        break;
    }
  }

  private void printVariable() {
    System.out.println("Name:");
    System.out.println(Variables.get(input.nextLine()).value());
  }

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    calc.run();
  }
  
}