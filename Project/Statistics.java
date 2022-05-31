public class Statistics extends Functions {
  public static Number abs(Number x) {
    Number k = x.get();
    if (k.value() < 0 ) {
      k.setValue(k.value() * -1);
    }
    return k;
  }

  public static Int ceil(Number x) {
    Int k = x.getInt();
    if (x.value() > 0) {
      k.setValue(k.intValue() + 1);
    }
    return k;
  }

  public static Int floor(Number x) {
    Int k = x.getInt();
    if (x.value() < 0) {
      k.setValue(k.intValue() - 1);
    }
    return k;
  }

  public static Number exp(Number x) { //e implementation
    int n = floor(x).intValue();
    double r1 = abs(floor(x)).value() - abs(x).value();
    double sum = exp(new Float(e, "e"), new Int(n, "n")).value();
    if (n < 0) {
      sum = 1 / exp(new Float(e, "e"), abs(new Int(n, "n"))).value();
    }
    if (x.value() > 0 && x.value() < 1) { // values between 0 and 1 are wonky and this fixes them
      n = 1;
      r1 = x.value();
      sum = 1.0;
    }
    Float r = new Float(r1, "r");
    double rs = exp(r, new Float(2, "rs")).value(); // r to second
    double rt = exp(r, new Float(3, "rt")).value(); // r to third
    double rf = exp(r, new Float(4, "rf")).value(); // r to fourth
    double rfi = exp(r, new Float(5, "rfi")).value(); // r to fifth
    sum *= (1 + r1 + (rs / 2) + (rt / 6) + (rf / 24) + (rfi / 120));
    Number k = x.get();
    k.setValue(sum);
    return k;
  }

  public static Number exp(Number x, Number y) {
    double res = 1.0;
    Int max = floor(new Float(y.value(), "max"));
    for (int i = 0; i < max.intValue(); i++) {
      res *= x.value();
    }
    Number j = x.get();
    j.setValue(res);
    return j;
  }

  private static Int[] scientificNotD(Number exp) { //numbers with no dec
    int n = 0;
    Number ex = exp.get();
    while ( ex.value() / 10 > 1 ) {
      n++;
      ex.setValue(ex.value() / 10);
    }
    Int[] res = {new Int(n, "n"), new Int(ex.value(), "new exp")};
    return res;
  }

  private static Int[] scientificNotU(Number exp) { //numbers with decimals
    int n = 0;
    Number ex = exp.get();
    while ( (abs(ex).value() - abs(exp).value()) / 10.0 < .09 ) {
      n--;
      ex.setValue(ex.value() * 10.0);
    }
    Int[] res = {new Int(n, "n"), new Int(ex.value(), "new exp")};
    return res;
  }

  public static Number ln(Number x) {
    if (x.value() < 0) {
      return new Float(0, "undefined"); // THROW ERROR LATER
    }
    Int[] k;
    if (x.value() > 0 && x.value() < 1) {
      k = scientificNotU(x); // makes sure numbers between 0 and 1 return the correct natural logs
    } else {
      k = scientificNotD(x);
    }
    Number res = x.get();
    Number temp = ln(new Float(sqrt(k[1]), "temp"), 1);
    res.setValue( (2 * temp.value()) + (2.3025851 * k[0].value() ) );
    return res;
  }

  public static Number ln(Number x , int i) {
    Number res = x.get();
    double y = (res.value() - 1) / (res.value() + 1);
    double yt = exp(new Float(y, "y"), new Int(3, "yt")).value(); // y to third
    double yf = exp(new Float(y, "y"), new Int(5, "yf")).value(); // y to fifth
    double ys = exp(new Float(y, "y"), new Int(7, "ys")).value(); // y to seventh
    res.setValue(2 * (y + (yt / 3) + (yf / 5) + (ys / 7)));
    return res;
  }

  /*
  *@param n any non-negative value you want to take the sqrt of
  *@return the approximate sqrt of n within a tolerance of 0.001%
  */
  private static double sqrt(Number n) {
    return sqrt(n.value(), 1.0);
  }

  private static double sqrt(double n, double guess) {
    if (Math.abs( (n / guess + guess) / 2 - guess) < (guess * 0.00001)) {
      return guess;
    } else {
      return sqrt(n, (n / guess + guess) / 2);
    }
  }
  /*

  public static Number log(Number x, Number y) {

  }

  public static Int gcd(Int x, Int y) {

  }

  public static Int lcm (Int x, Int y) {

  }
  */
  /*
  public static void main(String[] args) {
    Float x = new Float(1.22, "x");
    Float y = new Float(-420.0484838, "y");
    Float k = new Float(.56, "k");
    Float g = new Float(2.1, "g");
    //test();
    //System.out.println(ceil(x));
    System.out.println(exp(k));
    //System.out.println(sqrt(k));
    //System.out.println(exp(k, g));
    //System.out.println(k);
  }
  */

}
