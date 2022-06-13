/**
 * The equation class which stores an expression with the variable x, then substitutes
 */
public class Equation extends Variable {
  /**
   * The string of the equation with x
   */
  String equation;

  /**
   * The boolean to specify whether to evaluate in degrees
   */
  boolean isDegrees = true;

  /**
   * The basic constructor assuming its in degrees and taking the name and expression
   * @param in the expression with x to be substituted
   * @param name the name of the equation
   */
  public Equation(String in, String name) {
    super(name, "equation");
    equation = in;
  }

  /**
   * The basic constructor taking the name, expression and whether or not it is in degrees
   * @param in thhe expression with x to be substituted
   * @param name the name of the equation
   * @param isDegrees whether or not it is in degrees
   */
  public Equation(String in, String name, boolean isDegrees) {
    super(name, "equation");
    equation = in;
    this.isDegrees = isDegrees;
  }

  /**
   * Finds the output of the expression given a value
   * @param var the string of the variable, always going to be x for current state
   * @param val the value which will be subtituted for x
   * @return the double evaluation 
   */
  public double substitute(String var, double val) {
    String expression = equation;
    while (expression.contains(var)) {
      int index = expression.indexOf(var);
      expression = expression.substring(0, index) + val + expression.substring(index + 1);
    }
    return Parse.parse(expression, isDegrees).value();
  }

  /**
   * Goes through all values from start to end, incrementating by inc, and returns a list of points
   * @param start the starting value
   * @param end the ending value
   * @param inc the value incrementation
   * @param var the variable to be substitude, for the moment always x
   * @return the list of points which are created from subtitution
   */
  public List<Point> values(double start, double end, double inc, String var) {
    List<Point> out = new List<Point>();
    for (double i = start; i < end; i += inc) {
      try {
        out.add(new Point(i, substitute(var, i), "point"));
      } catch (IllegalArgumentException e) {
      }
    }
    return out;
  }

  /**
   * Returns the string of the equation
   */
  public String toString() {
    return equation;
  }

}
