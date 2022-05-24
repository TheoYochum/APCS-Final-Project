public class Float extends Variable implements Number {
  double value;

  public Float(double in) {
    value = in;
  }

  public Float(int in) {
    value = (double) in;
  }

  public Int getInt() {
    return new Int(value);
  }

  public Float getFloat() {
    return (Float) copy();
  }

  public Fraction getFraction() {
    return new Fraction((int) value);
  }

  public double value() {
    return value;
  }
}
