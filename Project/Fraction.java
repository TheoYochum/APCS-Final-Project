/**
 * Fraction variable which implements the number interface
 * Allows the storage of non integer values while avoiding
 * floating points, as well as simple Fraction operationsm
 */

public class Fraction extends Variable implements Number {
  private Int numerator;
  private Int denominator;

  public static void main(String[] args) {
    System.out.println(approx(0.2, 10));
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
   * Basic constructor taking a single int and storing it as a Fraction with denominator 1
   * @param num the integer which goes in the numerator
   * @param name the name of the variable
   */
  public Fraction(int num, String name) {
    super(name, "Fraction");
    numerator = new Int(num, this.name() + " Numerator");
    denominator = new Int(1, this.name() + " Denominator");
  }

  /**
   * Basic constructor taking in a single double and approximating it to a Fraction with a tolerance of 5 * 10 ^ -10
   * @param in the double to be approximated
   * @param name the name of the variable
   */
  public Fraction(double in, String name) {
    super(name, "Fraction");
    Fraction temp = approx(in, 10);
    numerator = temp.getNumerator();
    denominator = temp.getDenominator();
  }
  
  /**
   * Basic constructor taking in a single double and approximating it to a Fraction with a custom tolerance
   * @param in the double to be approximated
   * @param tolerance the set tolerance with the form of 5.0 * 10 ^ -n
   * @param name the name of the variable
   */
  public Fraction(double in, int tolerance, String name) {
    super(name, "Fraction");
    Fraction temp = approx(in, tolerance);
    numerator = temp.getNumerator();
    denominator = temp.getDenominator();
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

  /**
   * Creates a new Fraction with the numerator and denomonator flipped
   * @return a reference to the new reciprocal Fraction
   */
  public Variable reciprocal() {
    return new Fraction(denominator, numerator, this.name() +  " reciprocal");
  }

  public static Fraction approx(int in, int tolerance) {
    return new Fraction(in, "name");
  }

  /**
   * Uses continued fractions to evaluate 
   * @param in the decimal to be approximated
   * @param tolerance the scientific notation exponent, for the differnce in the form of 5 * 10 ^ -n
   * @return the fractional approximation of the provided touble, within the tolerance
   */
  public static Fraction approx(double in, int tolerance) {
    return contToFrac(floatToCont(new Float(in, "name"), tolerance));
  }

  /**
   * Evaluates a continued fraction in the form [a0;a1,a2,...,an]
   * @param in a string of the continued fraction in proper form
   * @return the double value of the continued fraction
   */
  public static double evalContFrac(String in) {
    String[] contFrac = in.substring(1, in.length() - 1).split("[;,]");
    return Integer.parseInt(contFrac[0]) + (1 / evalContFrac(contFrac, 1));
  }

  /**
   * A helper function for the evaluation of the continued fraction
   * @param in the string array of the continued fraction broken uo
   * @param index the index of the current calue to be evaluated
   * @return the reciprocal value of the current value plus the recursive call of the next value
   */
  private static double evalContFrac(String[] in, int index) {
    if (index == in.length - 1) {
      return Integer.parseInt(in[index]);
    }
    double sum = Integer.parseInt(in[index]);
    return sum + (1 / evalContFrac(in, index + 1));
  }

  /**
   * Converts a coninued fraction in the form [a0;a1,a2,...,an], to a standard fraction
   * Uses the algorithm discussed here: https://math.stackexchange.com/questions/3084970/how-to-convert-continued-fractions-into-normal-fractions
   * @param in a String of a continued fraction in the proper form
   * @return the Fraction variable representation of the continued fraction
   */
  public static Fraction contToFrac(String in) {
    String[] ary = in.substring(1, in.length() - 1).split("[;,]");
    return new Fraction(contToFrac(ary, ary.length - 1, 'h'), contToFrac(ary, ary.length - 1, 'k'), "test");
  }

  /**
   * A recursive helper function for converting the continued fraction to a standard fraction
   * Uses the algorithm discussed here: https://math.stackexchange.com/questions/3084970/how-to-convert-continued-fractions-into-normal-fractions
   * @param in an array of the continued fraction's terms
   * @param index the index of the term to be evaluated
   * @param mode what mode the function should be evaluated in and the corresponding return values
   * @return the sum of the n - 1 and n - 2 terms recurisve evaluating according to the algorithm
   */
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

  /**
   * UNFINISHED method that converts a fraction to a continued fraction
   * @param in the input Fraction to be converted
   * @return the string of hte continued fraction in the proper form [a0;a1,a2,...,an]
   */
  public static String fractToCont(Fraction in) {
    String out = "[";
    out += (int) in.value() + ";";
    return out.substring(0, out.length() - 1) + "]";
  }

  /**
   * Converts the float taken in into a continued Fraction in the proper form [a0;a1,a2,...,an]
   * Uses the algorith described here: https://youtu.be/f-86g6kSFG4
   * @param in the decimal value to be converted
   * @param tolerance the scientific notation exponent of the accepted tolerance according to 5.0 * 10 ^ -n
   * @return the proper coninued fraction form of the approximated decimal
   */
  public static String floatToCont(Float in, int tolerance) {
    int i = 1;
    String cont = floatToContHelp(in, 1);
    while (Statistics.abs(new Float(in.value() - evalContFrac(cont), "name")).value() > Statistics.pow(new Int(5, "test"), new Float(-1 * tolerance, "test")).value()) {
      i++;
      cont = floatToContHelp(in, i);
    }
    return cont;
  }

  /**
   * A helper function which is called with increasing continued fraction terms to approach the desired tolerance
   * Uses the algorithm described here: https://youtu.be/f-86g6kSFG4
   * @param in the decimal value to be approximated
   * @param terms the number of terms desired in the continued fraction
   * @return the proper sting form, [a0;a1,a2,...,an], of a contined fraction with the given terms
   */
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
