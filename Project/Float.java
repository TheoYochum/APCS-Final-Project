public class Float extends Variable implements Number {
  double value;

  public Float(double in, String name) {
    super(name, "Float");
    value = in;
  }

  public Float(int in, String name) {
    super(name, "Float");
    value = (double) in;
  }

  public Int getInt() {
    return new Int(value, this.name());
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
