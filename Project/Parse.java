import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;;

public class Parse {

  private static String[] trig = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot"};
  private static String[] operations = {"^", "*", "/", "+", "-"};
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (true) {
      System.out.println("Enter the equation:");
      System.out.println(Parse(input.nextLine()));
    }
  }

  public static Float Parse(String equation) {
    ArrayList<String> terms = Terms(equation);
    for (int i = 0; i < terms.size(); i++) {
      if (terms.get(i).contains("(")) {
        terms.set(i, Parentheses(terms.get(i)));
      }
    }
    return new Float(Double.parseDouble(evaluate(terms)), "equation");
  }

  private static ArrayList<String> Terms(String equation) {
    ArrayList<String> out = new ArrayList<String>();
    while (equation.contains("(")) {
      String regex = "[(](?:[^)(]+|[(](?:[^)(]+|[(][^)(]*[)])*[)])*[)]";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher =  pattern.matcher(equation);
      matcher.find();
      String block = matcher.group(0);
      int index = equation.indexOf(block);
      for (String term : equation.substring(0, index).split(" ")) {
        int termIndex = equation.indexOf(term);
        if (term.length() > 0) {
          out.add(term);
          equation = equation.substring(0, termIndex) + equation.substring(termIndex + term.length() + 1);
        }
      }
      out.add(block);
      if (index + block.length() + 1 < equation.length()) {
        equation = equation.substring(0, index) + equation.substring(index + block.length() + 1);
      } else {
        equation = equation.substring(0, index);
      }
    }
    for (String term : equation.split(" ")) {
      out.add(term);
    }
    return out;
  }

  private static String Parentheses(String in) {
    int start = in.indexOf('(');
    int end = in.lastIndexOf(')');
    String out;
    if (start != -1 && end != -1) {
      out = evaluate(in.substring(start + 1, end));
      return out;
    } else {
      out = evaluate(in);
      return out;
    }
  }

  private static String evaluate(String in) {
    while (in.contains("(")) {
      Parentheses(in);
    }
    String[] temp = in.split(" ");
    ArrayList<String> evaluate = new ArrayList<String>();
    for (String term : temp) {
      evaluate.add(term);
    }
    return evaluate(evaluate);
  }

  private static String evaluate(ArrayList<String> in) {
    for (String operation : trig) {
      while (in.indexOf(operation) != -1) {
        trig(in, operation, in.indexOf(operation));
      }
    }
    for (String operation : operations) {
      while (in.indexOf(operation) != -1) {
        operation(in, operation, in.indexOf(operation));
      }
    }
    return in.get(0);
  }

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

  private static void operation(ArrayList<String> in, String operation, int i) {
    Number one;
    Number two;
    switch ((in.get(i))) {
      case "^":
        one = new Float(Double.parseDouble(in.get(i - 1)), "one");
        two = new Float(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + new Float(Math.pow(one.value(), two.value()), one.name() + " to the power of " + two.name()));
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


}
