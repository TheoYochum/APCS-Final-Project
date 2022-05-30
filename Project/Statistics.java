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

  public static Number exp(Number x, Number y) {
    double res = 1.0;
    Int max = floor(new Float(y.value(), "max"));
    for (int i = 0; i < max.intValue(); i++) {
      res *= x.value();
    }
    Number k = x.get();
    k.setValue(res);
    return k;
  }

  /*
  public static Number log(Number x, Number y) {

  }

  public static Int gcd(Int x, Int y) {

  }

  public static Int lcm (Int x, Int y) {

  }
  public static void main(String[] args) {
    Float x = new Float(1.22, "x");
    Float y = new Float(-420.0484838, "y");
    Float k = new Float(3.3, "k");
    Float g = new Float(5.1, "g");
    //System.out.println(5.1 - 5.0);
    //System.out.println(ceil(x));
    System.out.println(exp(k, g));
    //System.out.println(k);
  }

  */

}
