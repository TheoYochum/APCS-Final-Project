import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parse {

  private static String[] trig = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arcsec", "arccsc", "arccot"};
  private static String[] operations = {"^", "*", "/", "+", "-"};
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (true) {
      System.out.println(Parse(input.nextLine()));
    }
  }

  private static Float Parse(String equation) {
    ArrayList<String> split = Split(equation);
    while (split.contains("(")) {
      Parentheses(split);
    }
    return new Float(Double.parseDouble(evaluate(split)), "equation");
  }

  public static ArrayList<String> Split(String equation) {
    ArrayList<String> out = new ArrayList<String>();
    String[] split = equation.split(" ");
    for (int i = 0; i < split.length; i++) {
      if (split[i].contains("(") || split[i].contains(")")) {
        out.add(split[i].substring(0, split[i].indexOf("(")));
        out.add("(");
        out.add(split[i].substring(split[i].indexOf("(") + 1, split[i].indexOf(")")));
        out.add(")");
      } else {
        out.add(split[i]);
      }
    }
    return out;
  }

  private static void Parentheses(ArrayList<String> in) {
    int start = -1;
    int end = -1;
    ArrayList<String> block = new ArrayList<String>();
    for (int i = 0; i < in.size(); i++) {
      if (in.get(i).equals("(")) {
        start = i;
      }
      if (in.get(i).equals(")")) {
        end = i;
        for (int j = start + 1; j < end; j++ ) {
          block.add(in.get(j));
        }
        for (int j = start; j <= end; j++) {
          in.remove(start);
        }
        in.add(start, evaluate(block));
      }

    }
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
