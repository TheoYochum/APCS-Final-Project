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
}
