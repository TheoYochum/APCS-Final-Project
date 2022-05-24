/**
 * Interface for all number variables
 * Allows ambiguous storage and guarantees
 * interoperability
 */

public interface Number {
  public Int getInt();
  public Float getFloat();
  public Fraction getFraction();
}
