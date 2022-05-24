/**
 * A variable designed to store points which 
 * can be used in calculations or graphing
 */

public class Point extends Variable {
  private Number xCor;
  private Number yCor;

  public Point(Number x, Number y, String name) {
    super(name, "Point");
    xCor = x;
    yCor = y;
  }

  public Number getX() {
    return xCor;
  }

  public Number getY() {
    return yCor;
  }

  public void setX(Number in) {
    xCor = in;
  }

  public void setY(Number in) {
    yCor = in;
  }
}
