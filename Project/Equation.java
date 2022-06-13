public class Equation extends Variable {
  String equation;
  boolean isDegrees = true;

  public Equation(String in, String name) {
    super(name, "equation");
    equation = in;
  }

  public Equation(String in, String name, boolean isDegrees) {
    super(name, "equation");
    equation = in;
    this.isDegrees = isDegrees;
  }

  public double substitute(String var, double val) {
    String expression = equation;
    while (expression.contains(var)) {
      int index = expression.indexOf(var);
      expression = expression.substring(0, index) + val + expression.substring(index + 1);
    }
    return Parse.Parse(expression, isDegrees).value();
  }

  public List<Point> values(double start, double end, double inc, String var) {
    List<Point> out = new List<Point>();
    for (double i = start; i < end; i += inc) {
      Point p1 = new Point(i, substitute(var, i), "point");
      out.add(p1);
    }
    return out;
  }

  public String toString() {
    return equation;
  }

}
