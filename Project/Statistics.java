public class Statistics extends Functions {
  public static Number abs(Number x) {
    Number k = x.copy();
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

  /*public static Number log(Number x, Number y) {

  }

  public static Int gcd(Int x, Int y) {

  }

  public static Int lcm (Int x, Int y) {

  }

  public static void main(String[] args) {
    Float x = new Float(1.22, "x");
    Float y = new Float(-420.0484838, "y");
    //System.out.println(ceil(x));
    System.out.println(abs(y));
    System.out.println(y);
  }
  */

}
