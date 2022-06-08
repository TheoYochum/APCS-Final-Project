import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;;

public class Parse {

  /**
   * Sets of operations to be conducted in order
   */
  private static String[] stats = {"abs", "ceil", "floor", "exp", "pow", "ln", "sqrt", "gcd", "lcm", "log"};
  private static String[] trig = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot"};
  private static String[] operations = {"^", "*", "/", "+", "-"};

  /**
   * Input loop
   * @param args Call arguments
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (true) {
      System.out.println("Enter the expression:");
      System.out.println(Parse(input.nextLine()));
    }
  }

  /**
   * Takes an infix expression and breaks it down and evaluates it,
   * if it has paretheses it recursively evaluates them until all are removed
   * @param expression A string in proper infix format, spaces between operators
   * @return A float value of the results of this expression
   */
  public static Float Parse(String expression) {
    ArrayList<String> terms = Terms(expression);
    for (int i = terms.size() - 1; i >= 0; i--) {
      if (terms.get(i).contains("(")) { // Targets any Term of parentheses and recursively breaks them down and evaluates them individually
        terms.set(i, Parse(terms.get(i).substring(1, terms.get(i).length() - 1)).value() + "");
      }
    } // Evaluates each term
    return new Float(Double.parseDouble(evaluate(terms)), "expression");
  }

  /**
   * Turns a string expression in proper infix form into an Arraylist,
   * uses regex to group parentheses
   * @param in an infix expression with proper formatting
   * @return an ArrayList of the expression broken down
   */
  private static ArrayList<String> Terms(String in) {
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
  private static String evaluate(ArrayList<String> in) {
    for (String operation : trig) {
      while (in.indexOf(operation) != -1) {
        trig(in, operation, in.indexOf(operation));
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
   * The set of switch statements to process trigonometric operations
   * @param in The expression ArrayList containing the operation
   * @param operation the operation to be evaluated
   * @param i the index of the operation which is to be evaluated
   */
  private static void trig(ArrayList<String> in, String operation, int i) {
    Angle angle;
    Float value;
    switch ((in.get(i))) {
      case "sin":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.sin(angle).value(), "Sine of " + angle.name()));
        in.remove(i + 1);
        break;
      case "cos":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.cos(angle).value(), "Cosine of " + angle.name()));
        in.remove(i + 1);
        break;
      case "tan":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.tan(angle).value(), "Tangent of " + angle.name()));
        in.remove(i + 1);
        break;
      case "sec":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.sec(angle).value(), "Cotangent of " + angle.name()));
        in.remove(i + 1);
        break;
      case "csc":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.csc(angle).value(), "Sin of " + angle.name()));
        in.remove(i + 1);
        break;
      case "cot":
        angle = new Angle(Double.parseDouble(in.get(i + 1)), true, "angle");
        in.set(i, "" + new Float(Trig.cot(angle).value(), "Sin of " + angle.name()));
        in.remove(i + 1);
        break;
      case "arcsin":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arcsin(value, true).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccos":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccos(value, true).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arctan":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arctan(value, true).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arcsec":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccsc(value, true).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccsc":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arcsec(value, true).value(), "Sin of " + value.name()));
        in.remove(i + 1);
        break;
      case "arccot":
        value = new Float(Double.parseDouble(in.get(i + 1)), "float");
        in.set(i, "" + new Float(Trig.arccot(value, true).value(), "Sin of " + value.name()));
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

  private static void stats(ArrayList<String> in, String operation, int i) {
    Number one;
    Number two;
    switch ((in.get(i))) {
      case "abs":
        //add in stats code
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
