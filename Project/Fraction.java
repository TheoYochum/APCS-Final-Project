public class Fraction extends Variable implements Number {
  public Float getFloat() {
    return new Float();
  }

  public Int getInt() {
    return new Int();
  }

  public Fraction getFraction() {
    return (Fraction) copy();
  }
  
  public double value() {
    return 0.0;
  }
}
