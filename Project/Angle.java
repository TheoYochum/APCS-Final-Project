/**
 * Angle variable which can store values 
 * in both degrees and radians and allows
 * easy transfer between them
 */

public class Angle extends Variable {
  private double value;
  private boolean degrees;

  /**
   * Basic constructor
   * @param measure the measure of the angle
   * @param isDegrees true if it is in degrees, false for radians
   * @param name name of the variable
   */
  public Angle(double measure, boolean isDegrees, String name) {
    super(name, "Angle");
    value = measure;
    degrees = isDegrees;
  }

  /**
   * A static method to convert from degrees to radians
   * @param deg the value in degrees
   * @return the value in radians
   */
  public static double degToRad(double deg) {
    return (deg * Math.PI) / 180;
  }

  /**
   * A static method to covnert from radians to degrees
   * @param rad the value in radians
   * @return the value in degrees
   */
  public static double radToDeg(double rad) {
    return (rad * 180) / Math.PI;
  }

  /**
   * The value of the angle
   */
  public double value() {
    return value;
  }

  /**
   * Whether or not the angle is measured in degrees
   * @return boolean true if it is in degrees
   */
  public boolean isDegrees() {
    return degrees;
  }

  /**
   * Whether or not the angle is measured in radians
   * @return boolean true if it is in radians
   */
  public boolean isRadians() {
    return !degrees;
  }

  /**
   * Converts the value to degrees and alters instace variables accordingly
   * @return the new value in degrees
   */
  public double setDegrees() {
    if (degrees) {
      return value;
    }
    value = degToRad(value);
    degrees = true;
    return value;
  }

  /**
   * Converts the value to radians and alters instance variables accordingly
   * @return the new value in radians
   */
  public double setRadians() {
    if (!degrees) {
      return value;
    }
    value = radToDeg(value);
    degrees = false;
    return value;
  }

  /**
   * Sets the value given a double
   * @param in
   */
  public void setValue(double in) {
    value = in;
  }

  /**
   *  Override toString
   *  @return toString
   */
  public String toString() {
    return "" + value();
  }
}
