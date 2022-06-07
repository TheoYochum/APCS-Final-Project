public class Statistics extends Functions {

  /**
   * Converts the value of the Number taken in into it's absolute value and returns a new Number with that absolute value
   * @param Number with a positive or negative value
   * @return the reference of a Number with the absolute value of the param
   */
  public static Number abs(Number x) {
    Number k = x.get();
    if (k.value() < 0 ) {
      k.setValue(k.value() * -1);
    }
    return k;
  }

  /**
   * Provides the reference of a Int that has the value of the Number taken in rounded up
   * @param Number with a unrounded value
   * @return the reference of a Int with the rounded up value of the param
   */
  public static Int ceil(Number x) {
    Int k = x.getInt();
    if (x.value() > 0) {
      k.setValue(k.intValue() + 1);
    }
    return k;
  }

  /**
   * Provides the reference of a Int that has the value of the Number taken in rounded down
   * @param Number with a unrounded value
   * @return the reference of a Int with the rounded down value of the param
   */
  public static Int floor(Number x) {
    Int k = x.getInt();
    if (x.value() < 0) {
      k.setValue(k.intValue() - 1);
    }
    return k;
  }

  /**
   * Provides the reference of a Number that has e taken to the power of the value of the Number taken in
   * @param Number with a value that e will be raised to
   * @return the reference of a Number with the value of e taken to the power of the value of the param
   */
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
      out = 1 / exp(new Float(e, "e"), new Int(abs(new Float(n, "n")).value(), "exp")).value();
    }
    Float r = new Float(r1, "r");
    double temp = 0.0;
    double sum = 0.0;
    for (int i = 0; i < 20; i++) {
      temp = exp(r, new Int(i, "rs")).value();
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

  /**
   * Provides the reference of a Number that has taken the value of the Number param to the power of the value of the Int param
   * @param Number with a value that will be the base
   * @param Int with a value that will be the exponent of the base
   * @return the reference of a Number with the value of Number param taken to the power of the value of the Int param
   */
  private static Number exp(Number x, Int y) { //works only with integer exponents
    double res = 1.0;
    Int max = floor(y);
    for (int i = 0; i < max.intValue(); i++) {
      res *= x.value();
    }
    Number j = x.get();
    j.setValue(res);
    return j;
  }

  /**
   * Uses e to provide the reference of a Number that has taken the value of the Number param to the power of the value of the other Number param
   * @param Number with a value that will be the base
   * @param Number with a value that will be the exponent of the base
   * @return the reference of a Number with the value of Number param taken to the power of the value of the other Number param
   */
  public static Number pow(Number x, Number y) {
    Float k = new Float(y.value(), "k");
    k.setValue(k.value() * ln(x).value());
    return exp(k);
  }

  /**
   * Provides the reference of a Number that is the natural log of the value of the Number param
   * @param Number with a value that will be used for natural log
   * @return the reference of a Number that is the natural log of the value of the Number param
   */
  public static Number ln(Number x) {
    if (x.value() < 0) {
      return new Float(0, "undefined"); // THROW ERROR LATER
    }
    if (x.value() > 1 && x.value() < sqrt(new Int(10, "ten")).value()) {
      return ln(x, 1);
    }
    Number[] k = sciNot(x);
    Float sqrtX = new Float(Math.sqrt(k[1].value()), "t");
    Number temp = ln(sqrtX, 1);
    temp.setValue( (2 * temp.value()) + (2.3025851 * k[0].value() ) );
    return temp;
  }

  private static Number ln(Number x , int j) {
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
  public static Float sqrt(Number n) {
    Float k = new Float(sqrt(n.value(), 1.0), "k");
    return k;
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
    Int tempX = abs(x).getInt();
    Int tempY = abs(y).getInt();
    if (x.intValue() < y.intValue()) {
      k = tempX.intValue() * ( (tempY.intValue()) / gcd(x,y).intValue());
    }
    else if (y.intValue() < x.intValue()) {
      k = tempY.intValue() * ( (tempX.intValue()) / gcd(x,y).intValue());
    }
    Int lcm = new Int(k, "lcm");
    return lcm;
  }

  public static Number log(Number x, Number y) {
    Float k = new Float(y.value(), "k");
    k.setValue(ln(y).value() / ln(x).value());
    return k;
  }

  private static Number[] sciNot(Number exp, int outdated) {
    String decimal;
    int count = 0;
    if (exp.value() == (int)exp.value()) {
      decimal = Integer.toString((int)exp.value());
      int temp = decimal.length();
      if ( decimal.charAt(temp-1) == '0' ) {
        for (int i = temp - 1; decimal.charAt(i) == '0'; i--) {
          count--;
          temp = i;
        }
      }
      decimal = decimal.substring(0, temp);
    } else {
      decimal  = Double.toString(exp.value());
      for (int i = decimal.indexOf(".") + 1; i < decimal.length(); i++) {
        count++;
      }
      decimal = decimal.substring(0, decimal.indexOf(".")) + decimal.substring(decimal.indexOf(".") + 1);
    }
    Number[] res = {new Int(count, "n"), new Float(Double.valueOf(decimal), "int simp")};
    return res;
  }

  private static Number[] sciNot(Number exp) {
    String decimal;
    int count = 0;
    decimal  = Double.toString(exp.value());
    if (exp.value() > 1) {
      count = -1 + decimal.indexOf(".");
      int temp = decimal.length();
      decimal = "" + decimal.charAt(0) + "." + decimal.substring(1, decimal.indexOf(".")) + decimal.substring(decimal.indexOf(".") + 1);
    } else if (exp.value() > 0 && exp.value() < 1) {
      Number ex = exp.get();
      while (ex.value() < 1) {
        count--;
        ex.setValue(ex.value() * 10);
      }
      decimal = Double.toString(ex.value());
    }
    Number[] res = {new Int(count, "n"), new Float(Double.valueOf(decimal), "int simp")};
    return res;
  }

} // end of class
