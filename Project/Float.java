/**
 * Floating point variable which stores value as doubles
 * 
 */

public class Float extends Variable implements Number {
  double value;

  /**
   * Basic constructor
   * @param in value of the float
   * @param name name of the variable
   */
  public Float(double in, String name) {
    super(name, "Float");
    value = in;
  }

  /**
   * Basic constructor, allows for initialization using an int
   * @param in integer value that will be converted to a float
   * @param name name of the variable
   */
  public Float(int in, String name) {
    super(name, "Float");
    value = (double) in;
  }

  /**
   * The implementation converting any Float to an Int
   * @return the reference of a new Int variable with the same value floored
   */
  public Int getInt() {
    return new Int(value, this.name());
  }

  /**
   * The implementation converting any Float to an identical Float with a new reference
   * @return A reference of an identical Float
   */
  public Float getFloat() {
    return (Float) copy();
  }

  /**
   * The implementation converting any Float to a Fraction 
   * @return A reference of a new Fraction cariable with an approximation
   */
  public Fraction getFraction() {
    return new Fraction((int) value, this.name());
  }

  /**
   * The value of the Float
   * @return a double of the value
   */
  public double value() {
    return value;
  }
}
