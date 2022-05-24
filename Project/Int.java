public class Int extends Variable implements Number {
  private int value;

  public Int getInt() {
    return (Int) copy();
  }

  public Float getFloat() {
    return new Float();
  }

  public Fraction getFraction() {
    return new Fraction();
  }

  public double value() {
    return (double) value;
  }

  public int intValue() {
    return value;
  }
}
