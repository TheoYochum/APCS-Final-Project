abstract class Variable {
  private String name;
  private String type;

  public Variable() {
    name = "blank";
    type = "blank";
  }

  public Variable(String nameIn, String typeIn) {
    name = nameIn;
    type = typeIn;
  }

  public Variable copy() {
    return null;
  }

  public double value() {
    System.out.println("Does not have a value");
    return 0.0;
  }

  public String name() {
    return name;
  }

  public String type() {
    return type;
  }
}
