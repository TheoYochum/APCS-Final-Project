/**
 * A variable designed to store points which 
 * can be used in calculations or graphing
 */

public class Point extends Variable {
  private Number xCor;
  private Number yCor;

  /**
   * Basic constructor taking an x and y coordinate
   * @param x the x coordinate of the point as a Number
   * @param y the y coordinate of the point as a Number
   * @param name the name of the variable
   */
  public Point(Number x, Number y, String name) {
    super(name, "Point");
    xCor = x;
    yCor = y;
  }

  /**
   * Provides the reference of the x coordinate
   * @return the reference of the x coordinate
   */
  public Number getX() {
    return xCor;
  }

  /**
   * Provides the reference of the y coordinate
   * @return the reference of the y coordinate
   */
  public Number getY() {
    return yCor;
  }

  /**
   * Sets the x coordinate
   * @param in the reference of the new x coordinate
   */
  public void setX(Number in) {
    xCor = in;
  }

  /**
   * Sets the y coordinate  
   * @param in the reference of the new y coordinate
   */
  public void setY(Number in) {
    yCor = in;
  }
}
