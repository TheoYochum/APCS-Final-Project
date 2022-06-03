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
    double r1 = abs(x).value() - abs(floor(x)).value();
    double out = exp(new Float(e, "e"), new Int(n, "n")).value();
    if (x.value() > 0 && x.value() < 1) { // values between 0 and 1 are wonky and this fixes them
      n = 1;
      r1 = x.value();
      out = 1.0;
    }
    if (n < 0) {
      out = 1 / exp(new Float(e, "e"), abs(new Int(n, "n"))).value();
    }
    Float r = new Float(r1, "r");
    double temp = 0.0;
    double sum = 0.0;
    for (int i = 0; i < 20; i++) {
      temp = exp(r, new Float(i, "rs")).value();
      int factorial = 1;
      for (int j = 1; j <= i; j++) {
        factorial *= j;
      }
      sum += temp / factorial;
    }
    out *= sum;
    Number k = x.get();
    k.setValue(out);
    return k;
  }

  public static Number exp(Number x, Number y) { //works only with integer exponents
    double res = 1.0;
    Int max = floor(y);
    for (int i = 0; i < max.intValue(); i++) {
      res *= x.value();
    }
    Number j = x.get();
    j.setValue(res);
    return j;
  }


  public static Number pow(Number x, Number y) {
    Float k = new Float(y.value(), "k");
    k.setValue(k.value() * ln(x).value());
    return exp(k);
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
    if (x.value() > 1 && x.value() < sqrt(new Int(10, "ten")).value()) {
      return ln(x, 1);
    }
    Int[] k;
    if (x.value() > 0 && x.value() < 1) {
      k = scientificNotU(x); // makes sure numbers between 0 and 1 return the correct natural logs
    } else {
      if (x.value() == (int)x.value()) {
        k = scientificNotD(x);
      } else {
        k = scientificNotU(x);
      }
    }
    Number res = x.get();
    Number temp = ln(new Float(sqrt(k[1]).value(), "temp"), 1);
    res.setValue( (2 * temp.value()) + (2.3025851 * k[0].value() ) );
    return res;
  }

  public static Number ln(Number x , int j) {
    Number res = x.get();
    double y1 = (res.value() - 1) / (res.value() + 1);
    Float y = new Float(y1, "y");
    double temp = 0.0;
    double sum = 0.0;
    for (int i = 1; i < 199; i += 2) {
      temp = exp(y, new Int(i, "exp")).value();
      sum += temp / i;
    }
    res.setValue(2 * sum);
    return res;
  }

  /*
  *@param n any non-negative value you want to take the sqrt of
  *@return the approximate sqrt of n within a tolerance of 0.001%
  */
  public static Number sqrt(Number n) {
    Number res = n.get();
    res.setValue(sqrt(n.value(), 1.0));
    return res;
  }

  private static double sqrt(double n, double guess) {
    if (Math.abs( (n / guess + guess) / 2 - guess) < (guess * 0.00001)) {
      return guess;
    } else {
      return sqrt(n, (n / guess + guess) / 2);
    }
  }

  public static Int gcd(Int x, Int y) {
    if (y.value() == 0) {
      return x;
    } else {
      return gcd(y, new Int(x.value() % y.value(), "remainder"));
    }
  }

  public static Int lcm (Int x, Int y) {
    int k = 0;
    if (x.intValue() == 0 && y.intValue() == 0) {
      k = 0;
    }
    Int newX = x.getInt();
    Int newY = y.getInt();
    while (newX.intValue() != newY.intValue()) {
      if (newX.intValue() < newY.intValue()) {
        newX.setValue(newX.intValue() + x.intValue());
      } else if (newY.intValue() < newX.intValue()) {
        newY.setValue(newY.intValue() + y.intValue());
      }
    }
    Int lcm = new Int(newY.intValue(), "lcm");
    return lcm;
  }
  /*
  Int tempX = abs(x).getInt();
  Int tempY = abs(y).getInt();
  if (x.intValue() < y.intValue()) {
  k = tempX.intValue() * ( (tempY.intValue()) / gcd(x,y).intValue());
}
else if (y.intValue() < x.intValue()) {
k = tempY.intValue() * ( (tempX.intValue()) / gcd(x,y).intValue());
}
Int lcm = new Int(k, "lcm");

  public static Number log(Number x, Number y) {

  }
  */

  public static void main(String[] args) {
    Float x = new Float(2.22, "x");
    Float y = new Float(-420.0484838, "y");
    Float k = new Float(10.23, "k");
    Float g = new Float(3.6, "g");
    Int a = new Int(16, "a");
    Int b = new Int(19, "b");
    //test();
    //System.out.println(ceil(x));
    System.out.println(lcm(a,b));
    //System.out.println(ln(k));
    //System.out.println(pow(k, g));
    //System.out.println(sqrt(k));
    //System.out.println(k);
  }

}
