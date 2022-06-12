public class Equation extends Variable {
  String equation;

  public Equation(String in) {
    equation = in;
  }

  public double substitute(String var, double val) {
    String expression = equation;
    while (expression.contains(var)) {
      int index = expression.indexOf(var);
      //System.out.println(expression);
      expression = expression.substring(0, index) + val + expression.substring(index + 1);
    }
    return Parse.Parse(expression).value();
  }

  public List<Point> values(double start, double end, double inc, String var) {
    List<Point> out = new List<Point>();
    for (double i = start; i < end; i += inc) {
      out.add(new Point(i, substitute(var, i), "point"));
    }
    return out;
  }

}
