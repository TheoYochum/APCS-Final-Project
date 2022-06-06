/**
 * Fraction variable which implements the number interface
 * Allows the storage of non integer values while avoiding
 * floating points, as well as simple Fraction operationsm
 */

public class Fraction extends Variable implements Number {
  private Int numerator;
  private Int denominator;


  public static void main(String[] args) {
    // Fraction test = approx(3.245);
    Fraction test2 = new Fraction(649, 200 , "test");
    // System.out.println(test);
    // System.out.println(test2);
    // test2.simplify();
    // System.out.println(test2);
    String test1 = floatToCont(new Float(Functions.pi, "test"), 20);
    System.out.println(test1);
    Fraction test3 = contToFrac(test1);
    System.out.println(test3);
    System.out.println(test3.value());
  }

  /**
   * Basic constructor taking two Ints and storing them in the numerator and denominator
   * @param num the Int in the Numerator
   * @param denom the Int in the Denominator
   * @param name the name of the variable
   */
  public Fraction(Int num, Int denom, String name) {
    super(name, "Fraction");
    numerator = num;
    denominator = denom;
  }

  /**
   * Basic constructor taking two Ints and storing them in the numerator and denominator
   * @param num the Int in the Numerator
   * @param denom the Int in the Denominator
   * @param name the name of the variable
   */
  public Fraction(int num, int denom, String name) {
    super(name, "Fraction");
    numerator = new Int(num, "numerator");
    denominator = new Int(denom, "numerator");
  }

  /**
   * Basic constructor taking a single int and storing it as a Fraction wiht denominator 1
   * @param num The integer which goes in the numerator
   * @param name the name of the variable
   */
  public Fraction(int num, String name) {
    super(name, "Fraction");
    numerator = new Int(num, this.name() + " Numerator");
    denominator = new Int(1, this.name() + " Denominator");
  }

  /**
   * Converts the Fraction to a Float with the value as the double
   * @return the reference of an equivalent Float
   */
  public Float getFloat() {
    return new Float(value(), this.name());
  }

  /**
   * Converts the Fraction to an Int with the value
   * @return the reference of an equivalent Int
   */
  public Int getInt() {
    return new Int(value(), this.name());
  }

  /**
   * Creates an identical Fraction
   * @return the reference of a sperate but identical Fraction
   */
  public Fraction getFraction() {
    return (Fraction) copy();
  }

  /**
   * The value of the numerator
   * @return the value of the numerator of the fraction
   */
  public Int getNumerator() {
    return numerator;
  }

  /**
   * The value of the denominator
   * @return the value of the denominator of the fraction
   */
  public Int getDenominator() {
    return denominator;
  }

  /**
   * The value of the Fraction, the numerator over the denominator
   * @return The numerator divided by the denominator
   */
  public double value() {
    return (double) numerator.intValue() / denominator.intValue();
  }

  public void setValue(int x) {
    //necesarry for overide
    numerator.setValue(x);
    denominator.setValue(1);
  }

  public void setValue(double x) {
    //necesarry for overide
    numerator.setValue((int)x);
    denominator.setValue(1);
  }

  /**
   * A method which attempts to reduce the fraction to its simplest form
   */
  public void simplify() {
    Int gcd = Statistics.gcd(numerator, denominator);
    numerator.setValue(Arithmetic.divide(numerator, gcd).getInt().intValue());
    denominator.setValue(Arithmetic.divide(denominator, gcd).getInt().intValue());
  }


  /**
   * Creates an identical Fraction with a different reference
   * @return a reference to an identical Fraction
   */
  public Variable copy() {
    return new Fraction(numerator, denominator, this.name());
  }

  public Variable reciprocal() {
    return new Fraction(denominator, numerator, this.name() +  " reciprocal");
  }

  // public static Fraction approx(double in) {
  //   int num = 0;
  //   int denom = 0;
  //   double sum = 0;
    
    

  //   return new Fraction(num, denom, "approx");
  // }

  public static int wholePart(Fraction in) {
    return (int) in.value();
  }

  public static double evalContFrac(String in) {
    String[] contFrac = in.substring(1, in.length() - 1).split("[;,]");
    return Integer.parseInt(contFrac[0]) + (1 / evalContFrac(contFrac, 1));
  }

  private static double evalContFrac(String[] in, int index) {
    if (index == in.length - 1) {
      return Integer.parseInt(in[index]);
    }
    double sum = Integer.parseInt(in[index]);
    return sum + (1 / evalContFrac(in, index + 1));
  }

  public static Fraction contToFrac(String in) { // https://math.stackexchange.com/questions/3084970/how-to-convert-continued-fractions-into-normal-fractions
    String[] ary = in.substring(1, in.length() - 1).split("[;,]");
    return new Fraction(contToFrac(ary, ary.length - 1, 'h'), contToFrac(ary, ary.length - 1, 'k'), "test");
  }

  private static int contToFrac(String[] in, int index, char mode) {
    if (index == -2) {
      if (mode == 'h') {
        return 0;
      } else {
        return 1;
      }
    } else if (index == -1) {
      if (mode == 'h') {
        return 1;
      } else {
        return 0;
      }
    }
    return Integer.parseInt(in[index]) * contToFrac(in, index - 1, mode) + contToFrac(in, index - 2, mode);
  }

  public static String fractToCont(Fraction in) {
    String out = "[";
    out += (int) in.value() + ";";
    return out.substring(0, out.length() - 1) + "]";
  }

  public static String floatToCont(Float in, int tolerance) {
    int i = 1;
    String cont = floatToContHelp(in, 1);
    while (Statistics.abs(new Float(in.value() - evalContFrac(cont), "name")).value() > Statistics.pow(new Int(5, "test"), new Float(-1 * tolerance, "test")).value()) {
      i++;
      cont = floatToContHelp(in, i);
    }
    return cont;
  }

  private static String floatToContHelp(Float in, int terms) {
    double x = in.value();
    int a = (int) x;
    String cont = "[" + a + ";";
    for (int i = 0; i < terms; i++) {
      x = 1 / (x - a);
      a = (int) x;
      cont += a + ",";
    }
    cont = cont.substring(0, cont.length() - 1) + "]"; 
    return cont;
  }
  

  /**
   * Provides the reference of the Fraction as a Number
   * @return the reference of the Fraction as a Number
   */
  public Number get() {
    return new Fraction(numerator, denominator, this.name());
  }

  /**
   * Constructs a string using the numberator and denominator of the Fraction object
   * @return a string version of the Fraction object
   */
  public String toString() {
    return "" + numerator + "/" + denominator;
  }
}
