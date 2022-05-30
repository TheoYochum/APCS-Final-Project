/**
 * Fraction variable which implements the number interface
 * Allows the storage of non integer values while avoiding
 * floating points, as well as simple Fraction operationsm
 */

public class Fraction extends Variable implements Number {
  private Int numerator;
  private Int denominator;

  /**
   * Basic constructor taking two Ints and storing them in the numerator and denomonator
   * @param num the Int in the Numerator
   * @param denom the Int in the Denominator
   * @param name the name of the variable
   */
  public Fraction(Int num, Int denom, String name) {
    super(name, "Fraction");
    numerator = num;
    denominator = denom;
  }

  /**
   * Basic constructor taking a single int and storing it as a Fraction wiht deonomonator 1
   * @param num The integer which goes in the numerator
   * @param name the name of the variable
   */
  public Fraction(int num, String name) {
    super(name, "Fraction");
    numerator = new Int(num, this.name() + " Numerator");
    denominator = new Int(1, this.name() + " Denominator");
  }

  /**
   * Converts the Fraction to a Float with the value as the double
   * @return the reference of an equivalent Float
   */
  public Float getFloat() {
    return new Float(value(), this.name());
  }

  /**
   * Converts the Fraction to an Int with the value
   * @return the reference of an equivalent Int
   */
  public Int getInt() {
    return new Int(value(), this.name());
  }

  /**
   * Creates an identical Fraction
   * @return the reference of a sperate but identical Fraction
   */
  public Fraction getFraction() {
    return (Fraction) copy();
  }

  /**
   * The value of the Fraction, the numerator over the denominator
   * @return The numerator divided by the denominator
   */
  public double value() {
    return (double) numerator.intValue() / denominator.intValue();
  }

  public void setValue(int x) {
    //necesarry for overide
  }

  public void setValue(double x) {
    //necesarry for overide
  }

  /**
   * A method which attempts to reduce the fraction to its simplest form
   */
  public void simplify() {
  }

  /**
   * Creates an identical Fraction with a different reference
   * @return a reference to an identical Fraction
   */
  public Variable copy() {
    return new Fraction(numerator, denominator, this.name());
  }

  /**
   * Provides the reference of the Fraction as a Number
   * @return the reference of the Fraction as a Number
   */
  public Number get() {
    return new Fraction(value, this.name());
  }
}
