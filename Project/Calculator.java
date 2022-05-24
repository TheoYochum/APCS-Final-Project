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
    Int numerator;
    Int denominator;
    Number xCor;
    Number yCor;
    boolean degrees;
    String name;
    System.out.println("Type:");
    String type = input.nextLine();
    type = type.toLowerCase();
    Display.clear();
    switch (type) {
      case "integer":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        intValue = input.nextInt();
        Display.clear();
        Variables.put(name, new Int(intValue, name));
        break;
      case "float":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        doubleValue = input.nextDouble();
        Display.clear();
        Variables.put(name, new Float(doubleValue, name));
        break;
      case "fraction":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Numerator:");
        numerator = new Int(input.nextInt(), name + " Numerator");
        System.out.println("Denominator:");
        denominator = new Int(input.nextInt(), name + " Denominator");
        Display.clear();
        Variables.put(name, new Fraction(numerator, denominator, name));
        break;
      case "point":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("X Coordinate:");
        xCor = newNumber();
        System.out.println("Y Coordinate:");
        yCor = newNumber();
        Display.clear();
        Variables.put(name, new Point(xCor, yCor, name));
        break;
      case "angle":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Measure");
        doubleValue = input.nextDouble();
        System.out.println("Is it in degrees? y/n");
        degrees = input.nextLine().toLowerCase().equals("y");
        Display.clear();
        Variables.put(name, new Angle(doubleValue, degrees, name));
    }
  }

  private Number newNumber() {
    Number out;
    String name;
    int intValue;
    double doubleValue;
    Int numerator;
    Int denominator;
    System.out.println("What type of Number");
    String type = input.nextLine();
    type = type.toLowerCase();
    switch(type) {
      case "integer":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        intValue = input.nextInt();
        Display.clear();
        out = new Int(intValue, name);
        Variables.put(name, (Variable) out);
        return out;
      case "float":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Value:");
        doubleValue = input.nextDouble();
        Display.clear();
        out = new Float(doubleValue, name);
        Variables.put(name, (Variable) out);
        return out;
      case "fraction":
        System.out.println("Name:");
        name = input.nextLine();
        Display.clear();
        System.out.println("Numerator:");
        numerator = new Int(input.nextInt(), name + " Numerator");
        System.out.println("Denominator:");
        denominator = new Int(input.nextInt(), name + " Denominator");
        Display.clear();
        out = new Fraction(numerator, denominator, name);
        Variables.put(name, (Variable) out);
        return out;
    }
    return null;
  }

  private void printVariable() {
    System.out.println("Name:");
    String name = input.nextLine();
    if (!Variables.containsKey(name)) {
      System.out.println("Not a valid variable name");
      Display.clear();
      return;
    }
    System.out.println(Variables.get(name).value());
    hold();
  }

  private void hold() {
    input.nextLine();
  }

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    calc.run();
  }
  
}