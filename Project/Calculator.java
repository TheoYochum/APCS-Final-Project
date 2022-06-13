import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
  HashMap<String, Variable> Variables;
  Scanner input;
  boolean isDegrees = true;

  public Calculator() {
    Variables = new HashMap<String, Variable>();
    input = new Scanner(System.in);
  }

  private void run() {
    Display.clear();
    boolean running = true;
    while(true) {
      Display.clear();
      Display.display();
      Display.printAt("Command:", 1, 3);
      String term = input.nextLine();
      if (term.equals("exit")) {
        return;
      }
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

      case "set radians":
        isDegrees = false;
        System.out.println("Done!");
        hold();
        break;

      case "set degrees":
        isDegrees = true;
        System.out.println("Done!");
        hold();
        break;

      case "help":
        String out = "";
        out += "Commands:\n";
        out += "New Variable: Creates a new variable\n";
        out += "Print variable: Prints a stored variable given its name\n";
        out += "List variable: Lists all the current variables\n";
        out += "Evaluate: Will evaluate a series of operations, can be stored as a variable\n";
        out += "Set radians: Sets the angle evaluation to radians\n";
        out += "Set degrees: Sets the angle evaluation to degrees\n";
        out += "Exit: Exits!\n";
        out += "Help: This!\n";
        out += "\n";
        out += "Variables:\n";
        out += "Integer: An integer value\n";
        out += "Float: A floating point decimal\n";
        out += "Fraction: A value with a rational integer numerator and denominator\n";
        out += "Point: A set of two values, x and y\n";
        out += "Angle: A value storing an angle that can be converted betwee degrees and radians\n";
        out += "Matrix: An integer matrix\n";
        out += "Equation: An equation, assumed to always be y in terms of x\n";
        out += "\n";
        out += "Proper syntax: All operations seperated by a space, including functions, no spaces between parentheses\n";
        out += "Examples: \n";
        out += "sin (20)\n";
        out += "sin 20\n";
        out += "log 2 8\n";
        out += "(3 + 8) * 3\n";
        Display.display();
        Display.printAt(out, 1, 3);
        hold();
        break;

      default:
        Display.display();
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
      case "equation":
        System.out.println("Enter the equation with x as the variable");
        String equation = input.nextLine();
        System.out.println("Is the equation in degrees? y/n");
        boolean isDegrees = input.nextLine().toLowerCase().equals("y");
        Variables.put(name, new Equation(equation, name, isDegrees));
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
    boolean isDegrees = true;
    System.out.println("Are the values in degrees? y/n");
    if (input.nextLine().toLowerCase().equals("n")) {
      isDegrees = false;
    }
    Parse.call(Variables, isDegrees);
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
