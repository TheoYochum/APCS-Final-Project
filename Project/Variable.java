/**
 * Abstract parent class of all variable objects/
 * Provides basic type and name as well as forces
 * a copy method
 */

abstract class Variable {
  private String name;
  private String type;


  /**
   * Default constructor if a name and type is not provided
   */
  public Variable() {
    name = "blank";
    type = "blank";
  }

  /**
   * Standard constructor which sets the obejcts
   * name and type
   * @param nameIn The name of the variable which may be used as the key name
   * @param typeIn The type of variable for use if it is stored as an ambiguous class
   */
  public Variable(String nameIn, String typeIn) {
    name = nameIn;
    type = typeIn;
  }

  /**
   * Enforces a copy method which will allow any subclasses
   * to provide an identical variable with a different reference
   * @return a seperate reference with the same data
   */
  public Variable copy() {
    return null;
  }

  /**
   * Guarentees that all Variable objects will have a value function.
   * Not all Variables have a clear thing to return and thus it will
   * error if it is called by an object which does not provide an implementation
   * @return A double value determined by Variable type
   */
  public double value() {
    throw new IllegalAccessError("This class does not have a value");
  }

  /**
   * Returns the Variable name
   * @return variable name
   */
  public String name() {
    return name;
  }

  /**
   * Type of the variable
   * @return Type of the variable
   */
  public String type() {
    return type;
  }
}
