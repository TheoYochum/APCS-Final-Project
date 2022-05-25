public class Statistics extends Functions {
  /*public static Number abs(Number x) {
    if (x.value() < 0 ) {
      return x.value() * -1;
    }
    return x.value();
  }*/

  public static Float ceil(Float x) {
    Int k = x.getInt();
    if (x.value() < 0) {
      x.setValue(k.intValue());
      return x.value();
    }
    x.setValue(k.intValue() + 1);
    return x.value();
  }

  /*public static Float floor(Float x) {

  }

  public static Number log(Number x, Number y) {

  }

  public static Int gcd(Int x, Int y) {

  }

  public static Int lcm (Int x, Int y) {

  }*/

  /*
  public static void main(String[] args) {
    Float x = new Float(1.22, "x");
    Float y = new Float(-420.0484838, "y");
    ceil(x);
    ceil(y);
    System.out.println(x + " " + y);
  }*/
}
