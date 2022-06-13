import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;


/**
 * The class which parses any expressions
 */
public class Parse {

  /**
   * Sets of operations to be conducted in order
   */
  private static HashMap<String,Variable> variables = new HashMap<String, Variable>();
  private static String[] constants = {"e", "pi", "tau"};
  private static String[] stats = {"abs", "ceil", "floor", "exp", "pow", "ln", "sqrt", "gcd", "lcm", "log"};
  private static String[] trig = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot"};
  private static String[] operations = {"^", "*", "/", "+", "-"};

  /**
   * Boolean to detect when to exit
   */
  private boolean done = false;

  /**
   * Input loop
   * @param args Call arguments
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (true) {
      try {
        System.out.println("Enter the expression:");
        System.out.println(parse(input.nextLine()));
      } catch (NumberFormatException e) {
        System.out.println("Improper formatting \n");
      } catch (IllegalArgumentException e) {
        System.out.println("Improper input \n");
      }
    }
  }

  /**
   * Calls the Parse function from calculator assuming its in degrees
   * @param in the hashmap of the variables
   */
  public static void call(HashMap<String, Variable> in) {
    call(in, true);
  }

  /**
   * Calls the Parse function from calculator
   * @param in the hashmap of the variables
   * @param isDegrees whether or not it is in degrees
   */
  public static void call(HashMap<String, Variable> in, boolean isDegrees) {
    variables = in;
    Scanner input = new Scanner(System.in);
    while (true) {
      try {
        Display.clear();
        Display.display();
        Display.printAt("Enter the expression, or exit to return to the main menu:", 1, 3);
        String line = input.nextLine();
        if (line.toLowerCase().equals("exit")) {
          return;
        }
        Float temp = parse(line, isDegrees);
        System.out.println(temp);
        System.out.println("Would you like to store this as a variable? y/n");
        if (input.nextLine().equals("y")) {
          System.out.println("Name:");
          in.put(input.nextLine(), temp);
        }
      } catch (NumberFormatException e) {
        System.out.println("Improper formatting \n");
      } catch (IllegalArgumentException e) {
        System.out.println("Improper input \n");
      }
    }
  }

  /**
   * An unfinished function intended to screen for improper formatting, faulty logic
   * @param expression the expression to be screened
   * @return whether or not it has proper formatiing
   */
  public static boolean screen(String expression) {
    int openCount = 0;
    int closeCount = 0;
    for (int i = 0; i < expression.length(); i++) {
      char current = expression.charAt(i);
      if (current == '(') {
        openCount++;
        if (i > 0 && !(expression.charAt(i - 1) == ' ' || expression.charAt(i - 1) == '(')) {
          System.out.println(expression);
          System.out.println(i + " " + (expression.charAt(i)));
          return false;
        }
      }
      if (current == ')') {
        closeCount++;
        if (i < expression.length() - 1 && !(expression.charAt(i + 1) == ' ' || expression.charAt(i + 1) == ')')) {
          System.out.println(expression);
          System.out.println(i + " " + (expression.charAt(i)));
          return false;
        }
      }
      if (contains(operations, "" + current)) {
        if (i > 0 && (expression.charAt(i - 1) != ' ' || i < expression.length() - 1 && expression.charAt(i + 1) != ' ')) {
          if (!(Character.isDigit(expression.charAt(i + 1)))) {
            return false;
          }
        }
      }
    }
    if (openCount != closeCount) {
      return false;
    }
    return true;
  }

  /**
   * A function to check the presence of a string in an array
   * @param list the array to be checked
   * @param val the value to check for
   * @return whether or not the value is found
   */
  private static boolean contains(String[] list, String val) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(val)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calls the parsing method assuming its in degrees
   * @param expression the expression to be parsed
   * @return the Float returned from the expression
   */
  public static Float parse(String expression) {
    return parse(expression, true);
  }

  /**
   * Takes an infix expression and breaks it down and evaluates it,
   * if it has paretheses it recursively evaluates them until all are removed
   * @param expression A string in proper infix format, spaces between operators
   * @return A float value of the results of this expression
   */
  public static Float parse(String expression, boolean isDegrees) {
    // if (!screen(expression)) {
    //   throw new NumberFormatException("Improper parentheses");
    // }
    ArrayList<String> terms = terms(expression);
    for (int i = terms.size() - 1; i >= 0; i--) {
      if (terms.get(i).contains("(")) { // Targets any Term of parentheses and recursively breaks them down and evaluates them individually
        terms.set(i, parse(terms.get(i).substring(1, terms.get(i).length() - 1)).value() + "");
      }
    } // Evaluates each term
    return new Float(Double.parseDouble(evaluate(terms, isDegrees)), "expression");
  }

  /**
   * Turns a string expression in proper infix form into an Arraylist,
   * uses regex to group parentheses
   * @param in an infix expression with proper formatting
   * @return an ArrayList of the expression broken down
   */
  private static ArrayList<String> terms(String in) {
    String expression = in;
    ArrayList<String> out = new ArrayList<String>();
    while (expression.contains("(")) { // As long as the string has parentheses left break them down
      String regex = "[(](?:[^)(]+|[(](?:[^)(]+|[(][^)(]*[)])*[)])*[)]"; // Appropriated from here: https://stackoverflow.com/questions/546433/regular-expression-to-match-balanced-parentheses
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher =  pattern.matcher(expression);
      matcher.find(); // RegEx operations
      String block = matcher.group(0);
      int index = expression.indexOf(block);
      if (index != 0) { // If the parentheses is not the first term then add every preceeding term
        String[] terms = expression.substring(0, index).split(" ");
        for (String term : terms) {
          out.add(term);
        }
      } // Add the parenthese block
      out.add(block);
      if (index + block.length() + 1 < expression.length()) { // Removes everything before and including the Parentheses block
        expression = expression.substring(index + block.length() + 1);
      } else {
        expression = "";
      }
    }
    String[] terms = expression.split(" "); // Once every parentheses is processed add the rest of the terms
    for (String term : terms) {
      out.add(term);
    }
    return out;
  }

  /**
   * Steps through each operation, checking if they are present
   * and then evaluating them
   * @param in An arraylist of an expression with all parentheses removed
   * @return a String of the value of the expression
   */
  private static String evaluate(ArrayList<String> in, boolean isDegrees) {
    for (String name : variables.keySet()) {
      while (in.indexOf(name) != -1) {
        in.set(in.indexOf(name), "" + variables.get(name).value());
      }
    }
    for (String value : constants) {
      while (in.indexOf(value) != -1) {
        constants(in, value, in.indexOf(value));
      }
    }
    for (String operation : trig) {
      while (in.indexOf(operation) != -1) {
        trig(in, operation, in.indexOf(operation), isDegrees);
      }
    }
    for (String operation : stats) {
      while (in.indexOf(operation) != -1) {
        stats(in, operation, in.indexOf(operation));
      }
    }
    for (String operation : operations) {
      while (in.indexOf(operation) != -1) {
        operation(in, operation, in.indexOf(operation));
      }
    }
    return in.get(0);
  }

  /**
   * The method to subsitute constants
   * @param in the arraylist of terms to be subtituted
   * @param operation the constant to be checked for
   * @param i the index of the value to be checked
   */
  private static void constants(ArrayList<String> in, String operation, int i) {
    Float value;
    switch (in.get(i)) {
      case "e":
        value = new Float(Functions.e, "e");
        in.set(i, "" + value);
        break;
      case "pi":
        value = new Float(Functions.pi, "pi");
        in.set(i, "" + value);
        break;
      case "tau":
        value = new Float(Functions.tau, "tau");
        in.set(i, "" + value);
        break;
    }
  }

  /**
   * The set of switch statements to process trigonometric operations
   * @param in The expression ArrayList containing the operation
   * @param operation the operation to be evaluated
   * @param i the index of the operation which is to be evaluated
   */
  private static void trig(ArrayList<String> in, String operation, int i, boolean isDegrees) {
    Angle angle;
    Float value;
    switch ((in.get(i))) {
      case "sin":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.sin(angle).value(), "Sine of " + angle.name()));
        in.remove(i + 1);
        break;
      case "cos":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.cos(angle).value(), "Cosine of " + angle.name()));
        in.remove(i + 1);
        break;
      case "tan":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.tan(angle).value(), "Tangent of " + angle.name()));
        in.remove(i + 1);
        break;
      case "sec":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.sec(angle).value(), "Cotangent of " + angle.name()));
        in.remove(i + 1);
        break;
      case "csc":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.csc(angle).value(), "Sin of " + angle.name()));
        in.remove(i + 1);
        break;
      case "cot":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), isDegrees, "angle");
        in.set(i, "" + new Float(Trig.cot(angle).value(), "Sin of " + angle.name()));
        in.remove(i + 1);
        break;
      case "arcsin":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arcsin(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccos":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccos(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arctan":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arctan(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arcsec":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccsc(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccsc":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arcsec(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccot":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccot(value, isDegrees).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
    }
  }

  /**
   * The set of switch statements to process arithmetic operations
   * @param in The expression ArrayList containing the operation
   * @param operation the operation to be evaluated
   * @param i the index of the operation which is to be evaluated
   */
  private static void operation(ArrayList<String> in, String operation, int i) {
    Number one;
    Number two;
    switch ((in.get(i))) {
      case "^":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + new Float(Statistics.pow(one, two).value(), one.name() + " to the power of " + two.name()));
        in.remove(i);
        in.remove(i);
        break;
      case "*":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.multiply(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "/":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.divide(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "+":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.add(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "-":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.subtract(one, two));
        in.remove(i);
        in.remove(i);
        break;
    }
  }

  /**
   * Carries out all the statistical operators
   * @param in the terms to be substituted
   * @param operation the operation to be substituted
   * @param i the index of the value to be substituted
   */
  private static void stats(ArrayList<String> in, String operation, int i) {
    Number one;
    Number two;
    switch (in.get(i)) {
      case "abs":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.abs(one));
        in.remove(i + 1);
        break;
      case "ceil":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.ceil(one));
        in.remove(i + 1);
        break;
      case "floor":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.floor(one));
        in.remove(i + 1);
        break;
      case "exp": //e
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.exp(one));
        in.remove(i + 1);
        break;
      case "pow":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 2)), "two");
        in.set(i, "" + Statistics.pow(one, two));
        in.remove(i + 1);
        in.remove(i + 1);
        break;
      case "ln":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.ln(one));
        in.remove(i + 1);
        break;
      case "sqrt":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        in.set(i, "" + Statistics.sqrt(one));
        in.remove(i + 1);
        break;
      case "gcd":
        one = new Int(Double.parseDouble(in.get(i + 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 2)), "two");
        in.set(i, "" + Statistics.gcd(one.getInt(), two.getInt()));
        in.remove(i + 1);
        in.remove(i + 1);
        break;
      case "lcm":
        one = new Int(Double.parseDouble(in.get(i + 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 2)), "two");
        in.set(i, "" + Statistics.lcm(one.getInt(), two.getInt()));
        in.remove(i + 1);
        in.remove(i + 1);
        break;
      case "log":
        one = new Float(Double.parseDouble(in.get(i + 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 2)), "two");
        in.set(i, "" + Statistics.log(one, two));
        in.remove(i + 1);
        in.remove(i + 1);
        break;
    }
  }
}
