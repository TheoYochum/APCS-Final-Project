public class Float extends Variable implements Number {
  double value;

  public Int getInt() {
    return new Int();
  }

  public Float getFloat() {
    return (Float) copy();
  }

  public Fraction getFraction() {
    return new Fraction();
  }

  public double value() {
    return value;
  }
}
