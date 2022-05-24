public class Fraction extends Variable implements Number {
  private Int numerator;
  private Int denominator;

  public Fraction(Int num, Int denom) {
    numerator = num;
    denominator = denom;
  }

  public Fraction(int num) {
    numerator = new Int(num);
    denominator = new Int(1);
  }

  public Float getFloat() {
    return new Float(value());
  }

  public Int getInt() {
    return new Int(value());
  }

  public Fraction getFraction() {
    return (Fraction) copy();
  }
  
  public double value() {
    return (double) numerator.intValue() / denominator.intValue();
  }

  public void simplify() {
  }

  public Variable copy() {
    return new Fraction(numerator, denominator);
  }
}
