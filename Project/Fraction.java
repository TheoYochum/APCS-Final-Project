public class Fraction extends Variable implements Number {
  private Int numerator;
  private Int denominator;

  public Fraction(Int num, Int denom, String name) {
    super(name, "Fraction");
    numerator = num;
    denominator = denom;
  }

  public Fraction(int num) {
    numerator = new Int(num, this.name() + " Numerator");
    denominator = new Int(1, this.name() + " Denominator");
  }

  public Float getFloat() {
    return new Float(value(), this.name());
  }

  public Int getInt() {
    return new Int(value(), this.name());
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
    return new Fraction(numerator, denominator, this.name());
  }
}
