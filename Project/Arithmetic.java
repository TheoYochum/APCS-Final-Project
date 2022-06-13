
/**
 * A function for normal arithmetic operations on all Numbers
 */
public class Arithmetic extends Functions {

  /**
   * Adds the values of both numbers
   * @param one The first number to be added
   * @param two The second number to be added
   * @return the sum of the values
   */
  public static Float add(Number one, Number two) {
    return new Float(one.value() + two.value(), one.name() + " plus " + two.name());
  }

  /**
   * Subtracts the values of both numbers
   * @param one the first number to be subtracted
   * @param two the second number to be subtracted
   * @return the difference of the values
   */
  public static Float subtract(Number one, Number two) {
    return new Float(one.value() - two.value(), one.name() + " minus " + two.name());
  }

  /**
   * Multiplies the values of the two numbers
   * @param one the first number to be multiplied
   * @param two the second number to be mutliplied
   * @return the products of the values
   */
  public static Float multiply(Number one, Number two) {
    return new Float(one.value() * two.value(), one.name() + " times " + two.name());
  }

  /**
   * Divides the values of the two numbers
   * @param one the divisor
   * @param two the dividend
   * @return the quotient of the two numbers
   */
  public static Float divide(Number one, Number two) {
    return new Float(one.value() / two.value(), one.name() + " divided by " + two.name());
  }
}
