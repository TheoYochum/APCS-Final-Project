/**
 * Interface for all number variables
 * Allows ambiguous storage and guarantees
 * interoperability
 */

public interface Number {
  /**
   * Provides the reference of the Number converted to an Int
   * @return the reference of the Number converted to an Int
   */
  public Int getInt();

  /**
   * Provides the reference of the Number converted to a Float
   * @return the reference of the Number converted to a Float
   */
  public Float getFloat();

  /**
   * Provides the reference of the Number converted to a Fraction
   * @return the reference of the Number converted to a Fraction
   */
  public Fraction getFraction();

  /**
   * The value of the Number
   * @return a double of the value of a Number
   */
  public double value();

  /**
   * Sets the value of a Number to a double int value
   */
  public void setValue(double x);

  /**
   * Sets the value of a Number to a new int value
   */
  public void setValue(int x);
}
