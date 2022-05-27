/**
 * Integer variable to abstract certain functions
 */

public class Int extends Variable implements Number {
  private int value;

  /**
   * Basic constructor taking a double and rounding it for the int value
   * @param in the double value to be stored as an int
   * @param name the name of the variable
   */
  public Int(double in, String name) {
    super(name, "Int");
    value = (int) in;
  }

  /**
   * Basic constructor taking an int as the value
   * @param in the int value
   * @param name the name of the variable
   */
  public Int(int in, String name) {
    super(name, "Int");
    value = in;
  }

  /**
   * Provides the reference of an identical Int with a unique reference
   * @return the reference of an identican Int
   */
  public Int getInt() {
    return (Int) copy();
  }

  /**
   * Provides the reference of a Float created with the same value
   * @return the reference of an Float with the same value
   */
  public Float getFloat() {
    return new Float(intValue(), this.name());
  }

  /**
   * Provides the reference of a Fraction created with the same value
   * @return the reference of an Identical Fraction
   */
  public Fraction getFraction() {
    return new Fraction(intValue(), this.name());
  }

  /**
   * The value as a double
   * @return the value as a double
   */
  public double value() {
    return (double) value;
  }

  /**
   * The value as an int when an Int can be specifically referenced
   * @return the value as an int
   */
  public int intValue() {
    return value;
  }

  /**
   * Sets the value of a Int to a new int value
   */
  public void setValue(int x) {
    value = x;
  }

  /**
   * Sets the value of a Int to a new double value
   */
  public void setValue(double x) {
    value = (int)x;
  }

  /**
   * Provides the reference of an Identical int
   * @return the reference of an Identical int
   */
  public Number copy() {
    return new Int(value, this.name());
  }

  /**
   * Constructs a string using the value of the Int object
   * @return a string version of the Int value
   */
  public String toString() {
    return "" + intValue();
  }
}
