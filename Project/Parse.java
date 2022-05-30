import java.util.ArrayList;

public class Parse {

  private static String[] operations = {"^", "*", "/", "+", "-"};
  
  public static void main(String[] args) {
    String test = "2 + (4 * 3) + 2";
    System.out.println(Parse(test));
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
        for (int j = 0; j < split[i].length(); j++) {
          out.add("" + split[i].charAt(j));
        }
      } else {
        out.add("" + split[i].charAt(0));
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
    for (String operation : operations) {
      while (in.indexOf(operation) != -1) {
        operation(in, operation, in.indexOf(operation));
      }
    }
    return in.get(0);
  }

  private static void operation(ArrayList<String> in, String operation, int i) {
    Number one;
    Number two;
    switch ((in.get(i))) {
      case "^":
        one = new Int(Double.parseDouble(in.get(i - 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + new Float(Math.pow(one.value(), two.value()), one.name() + " to the power of " + two.name()));
        in.remove(i);
        in.remove(i);
        break;
      case "*":
        one = new Int(Double.parseDouble(in.get(i - 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.multiply(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "/":
        one = new Int(Double.parseDouble(in.get(i - 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.divide(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "+":
        one = new Int(Double.parseDouble(in.get(i - 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.add(one, two));
        in.remove(i);
        in.remove(i);
        break;
      case "-":
        one = new Int(Double.parseDouble(in.get(i - 1)), "one");
        two = new Int(Double.parseDouble(in.get(i + 1)), "two");
        in.set(i - 1, "" + Arithmetic.subtract(one, two));
        in.remove(i);
        in.remove(i);
        break;
    }
  }

}
