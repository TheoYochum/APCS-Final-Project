public class Statistics extends Functions {
  public static Number abs(Number x) {
    Number k = x.get();
    if (k.value() < 0 ) {
      k.setValue(k.value() * -1);
    }
    return k;
  }

  public static Int ceil(Float x) {
    Int k = x.getInt();
    if (x.value() > 0) {
      k.setValue(k.intValue() + 1);
    }
    return k;
  }

  public static Int floor(Float x) {
    Int k = x.getInt();
    if (x.value() < 0) {
      k.setValue(k.intValue() - 1);
    }
    return k;
  }

  public static Number expInt(Number x, Number y) {
    double res = 1.0;
    //Int[] k = scientificNot(y);
    Int max = floor(new Float(y.value(), "max"));
    for (int i = 0; i < max.intValue(); i++) {
      res *= x.value();
    }
    Number j = x.get();
    j.setValue(res);
    return j;
  }

  /*
  public static void test() {
    Int[] k = scientificNot(new Float(2.1, "k"));
    System.out.println(k[0]);
  }
  */

  private static Int[] scientificNot(Number exp) {
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
    double y = (x.value() - 1) / (x.value() + 1);
    Number res = x.get();
    double yt = expInt(new Float(y, "y"), new Int(3, "yt")).value(); // y to third
    double yf = expInt(new Float(y, "y"), new Int(5, "yf")).value(); // y to fifth
    double ys = expInt(new Float(y, "y"), new Int(7, "ys")).value(); // y to seventh
    res.setValue(2 * (y + (yt / 3) + (yf / 5) + (ys / 7)));
    return res;
  }
  /*

  public static Number log(Number x, Number y) {

  }

  public static Int gcd(Int x, Int y) {

  }

  public static Int lcm (Int x, Int y) {

  }
  */
  public static void main(String[] args) {
    Float x = new Float(1.22, "x");
    Float y = new Float(-420.0484838, "y");
    Float k = new Float(4.0, "k");
    Float g = new Float(2.1, "g");
    //test();
    //System.out.println(ceil(x));
    //System.out.println(ln(k));
    //System.out.println(exp(k, g));
    //System.out.println(k);
  }


}
