public class Trig extends Functions {
  public static Float sin(Angle in) {
    double val = Math.sin(in.value());
    return new Float(val, in.name() + " sine");
  }

  public static Float cos(Angle in) {
    double val = in.value();
    if (in.isDegrees()) {
      val = 90 - val;
    } else {
      val = pi / 2;
    }
    Angle shifted = new Angle(val, in.isDegrees(), in.name() + " cosine shifted");
    return new Float(sin(shifted).value(), in.name() + "cosine");
  }

  public static Float tan(Angle in) {
    double val = sin(in).value() / cos(in).value();
    return new Float(val, in.name() + " tangent");
  }

  public static Float csc(Angle in) {
    double val = 1 / sin(in).value();
    return new Float(val, in.name() + " secant");
  }

  public static Float sec(Angle in) {
    double val = 1 / cos(in).value();
    return new Float(val, in.name() + " cosecant");
  }

  public static Float cot(Angle in) {
    double val = 1 / tan(in).value();
    return new Float(val, in.name() + " cotangent");
  }

  public static Angle arctan(Float in, boolean isDegrees) {
    double val = Math.atan(in.value());
    return new Angle(val, isDegrees, in.name() + " arctangent");
  }

  public static Angle arccos(Float in, boolean isDegrees) {
    Float shfited = new Float(Math.sqrt(1 - Math.pow(in.value(), 2)) / in.value(), in.name() + " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arccosine");
  }

  public static Angle arcsin(Float in, boolean isDegrees) {
    Float shfited = new Float(in.value() / Math.sqrt(1 - Math.pow(in.value(), 2)), in.name() + " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arcsine");
  }

  public static Angle arcsec(Float in, boolean isDegrees) {
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arccos(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arcsecant");
  }

  public static Angle arccsc(Float in, boolean isDegrees) {
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arcsin(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arccosecant");
  }

  public static Angle arccot(Float in, boolean isDegrees) {
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arctangent");
  }
}