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
      case "add variable":
        addVariable();
        break;
      
      case "print variable":
        printVariable();
        break;
    }
  }

  private void addVariable() {
    String name;
    System.out.println("Type:");
    String type = input.nextLine();
    Display.clear();
    switch (type) {
      case "int":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        int value = input.nextInt();
        Display.clear();
        Variables.put(name, new Int(value));
    }
  }

  private void printVariable() {
    System.out.println("Name:");
    System.out.println(((Int) Variables.get(input.nextLine())).value());
  }

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    calc.run();
  }
  
}