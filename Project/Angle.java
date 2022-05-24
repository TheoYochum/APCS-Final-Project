public class Angle extends Variable {
  private double value;
  private boolean degrees;

  public static double degToRad(double deg) {
    return (deg * Math.PI) / 180;
  }

  public static double radToDeg(double rad) {
    return (rad * 180) / Math.PI;
  }

  public double value() {
    return value;
  }

  public boolean isDegrees() {
    return degrees;
  }

  public boolean isRadians() {
    return !degrees;
  }

  public double setDegrees() {
    if (degrees) {
      return value;
    }
    value = degToRad(value);
    degrees = true;
    return value;
  }

  public double setRadians() {
    if (!degrees) {
      return value;
    }
    value = radToDeg(value);
    degrees = false;
    return value;
  }
}
