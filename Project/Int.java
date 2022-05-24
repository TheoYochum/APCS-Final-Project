public class Int extends Variable implements Number {
  private int value;

  public Int(double in) {
    value = (int) in;
  }

  public Int(int in) {
    value = in;
  }

  public Int getInt() {
    return (Int) copy();
  }

  public Float getFloat() {
    return new Float(intValue());
  }

  public Fraction getFraction() {
    return new Fraction(intValue());
  }

  public double value() {
    return (double) value;
  }

  public int intValue() {
    return value;
  }

  public Variable copy() {
    return new Int(value);
  }
}
