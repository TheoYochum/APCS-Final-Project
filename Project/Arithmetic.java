public class Arithmetic extends Functions {
  
  public static Float add(Number one, Number two) {
    return new Float(one.value() + two.value(), one.name() + " plus " + two.name());
  }

  public static Float subtract(Number one, Number two) {
    return new Float(one.value() - two.value(), one.name() + " minus " + two.name());
  }

  public static Float multiply(Number one, Number two) {
    return new Float(one.value() * two.value(), one.name() + " times " + two.name());
  }

  public static Float divide(Number one, Number two) {
    return new Float(one.value() / two.value(), one.name() + " divided by " + two.name());
  }
}
