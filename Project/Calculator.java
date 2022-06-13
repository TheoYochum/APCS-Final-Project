import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
  HashMap<String, Variable> Variables;
  Scanner input;

  public Calculator() {
    Variables = new HashMap<String, Variable>();
    input = new Scanner(System.in);
  }

  private void run() {
    Display.clear();
    boolean running = true;
    while(running) {
      Display.clear();
      Display.display();
      Display.printAt("Command:", 1, 3);
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
      
      case "list variables":
        listVariable();
        break;

      case "evaluate":
        evaluate();
        break;

      default:
        Display.printAt("Invalid Command", 1, 2);
        hold();
        break;
    }
  }

  public Variable newVariable() {
    String name;
    Display.display();
    Display.printAt("Type:", 1, 3);
    String type = input.nextLine();
    type = type.toLowerCase();
    Display.printAt("Name:", 1, 5);
    name = input.nextLine();
    return parser(type, name);
  }

  public Number newNumber() {
    String name;
    System.out.println("What type of Number");
    String type = input.nextLine();
    type = type.toLowerCase();
    System.out.println(type);
    System.out.println("Name:");
    name = input.nextLine();
    return (Number)(parser(type, name));
  }

  private Variable parser(String type, String name) {
    int intValue;
    double doubleValue;
    Int numerator;
    Int denominator;
    Number xCor;
    Number yCor;
    boolean degrees;
    Variable out = null;
    switch (type) {
      case "integer":
        System.out.println("Value:");
        intValue = input.nextInt();
        out = new Int(intValue, name);
        Variables.put(name, new Int(intValue, name));
        break;
      case "float":
        System.out.println("Value:");
        doubleValue = input.nextDouble();
        out = new Float(doubleValue, name);
        Variables.put(name, new Float(doubleValue, name));
        break;
      case "fraction":
        System.out.println("Numerator:");
        numerator = new Int(input.nextInt(), name + " Numerator");
        System.out.println("Denominator:");
        denominator = new Int(input.nextInt(), name + " Denominator");
        out = new Fraction(numerator, denominator, name);
        Variables.put(name, new Fraction(numerator, denominator, name));
        break;
      case "point":
        System.out.println("X Coordinate:");
        xCor = newNumber();
        System.out.println("Y Coordinate:");
        yCor = newNumber();
        out = new Point(xCor, yCor, name);
        Variables.put(name, new Point(xCor, yCor, name));
        break;
      case "angle":
        System.out.println("Measure:");
        doubleValue = input.nextDouble();
        System.out.println("happens");
        hold();
        System.out.println("Is it in degrees? y/n");
        degrees = input.nextLine().toLowerCase().equals("y");
        out = new Angle(doubleValue, degrees, name);
        Variables.put(name, new Angle(doubleValue, degrees, name));
        break;
      case "matrix":
        Matrix matrix = new Matrix(name);
        Variables.putIfAbsent(name, matrix);
        break;
      default:
        System.out.println("Invalid Type");
        hold();
        return null;
    }
    return out;
  }

  private void printVariable() {
    Display.display();
    Display.printAt("Name:", 1, 3);
    String name = input.nextLine();
    if (!Variables.containsKey(name)) {
      Display.printAt("Not a valid variable name", 1, 2);
      hold();
      return;
    }
    System.out.println(Variables.get(name).value());
    hold();
  }

  public void listVariable(){
    Display.display();
    Display.goTo(1, 3);
    for (String key : Variables.keySet()) {
      System.out.println("Type: " + Variables.get(key).type());
      System.out.println("Name: " + key);
      System.out.println("Value: " + Variables.get(key));
      System.out.println();
    }
    hold();
  }

  public void evaluate() {
    Parse.call(Variables);
  }

  private void hold() {
    System.out.println("Press enter to contine");
    input.nextLine();
  }

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    calc.run();
  }
}
