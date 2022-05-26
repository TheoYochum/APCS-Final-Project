/**
 * A set of trigonometric functions, 
 * all either dependnet on sin or arctan
 * Currently uses the taylor series for sin up to 5 iterations and 
 * series function for arctan
 */
public class Trig extends Functions {
    
  public static Float sin(Angle in) {
    double val = in.value();
    val = constrain(val, in.isDegrees());
    if (in.isDegrees()) {
      val = Angle.degToRad(val);
    }
    double sum = 0.0;
    double temp;
    int seq;
    for (int i = 1; i <= 5; i++) {
      seq = (i - 1) * 2 + 1;
      temp = 0;
      temp += Math.pow(val, seq);
      int factorial = 1;
      for (int j = seq; j > 0; j--) {
        factorial *= j;
      }
      temp /= factorial;
      if (i % 2 == 0) {
        temp *= -1;
      }
      sum += temp;
    }
    return new Float(sum, in.name() + " sine");
  }

  public static Float cos(Angle in) {
    double val = in.value();
    val = constrain(val, in.isDegrees());
    if (in.isDegrees()) {
      val = 90.0 - val;
    } else {
      val = (pi / 2.0) - val;
    }
    Angle shifted = new Angle(val, in.isDegrees(), in.name() + " cosine shifted");
    return new Float(sin(shifted).value(), in.name() + "cosine");
  }

  public static Float tan(Angle in) {
    double val = sin(in).value() / cos(in).value();
    val = constrain(val, in.isDegrees());
    return new Float(val, in.name() + " tangent");
  }

  public static Float csc(Angle in) {
    double val = 1 / sin(in).value();
    val = constrain(val, in.isDegrees());
    return new Float(val, in.name() + " secant");
  }

  public static Float sec(Angle in) {
    double val = 1 / cos(in).value();
    val = constrain(val, in.isDegrees());
    return new Float(val, in.name() + " cosecant");
  }

  public static Float cot(Angle in) {
    double val = 1 / tan(in).value();
    val = constrain(val, in.isDegrees());
    return new Float(val, in.name() + " cotangent");
  }

  public static Angle arctan(Float in, boolean isDegrees) {
    double val = in.value();
    double sum = 0.0;
    double temp;
    int seq;
    for (int i = 1; i <= 25; i++) {
      seq = (i - 1) * 2 + 1;
      temp = 0;
      temp += Math.pow(val, seq);
      temp /= seq;
      if (i % 2 == 0) {
        temp *= -1;
      }
      sum += temp;
    }
    if (isDegrees) {
      sum = Angle.radToDeg(sum);
    }
    return new Angle(sum, isDegrees, in.name() + " arctangent");
  }

  public static Angle arccos(Float in, boolean isDegrees) {
    if (in.value() > 1 || in.value() < -1) {
      throw new IllegalArgumentException("Input is not between -1 and 1");
    }
    Float shfited = new Float(Math.sqrt(1 - Math.pow(in.value(), 2)) / in.value(), in.name() + " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arccosine");
  }

  public static Angle arcsin(Float in, boolean isDegrees) {
    if (in.value() > 1 || in.value() < -1) {
      throw new IllegalArgumentException("Input is not between -1 and 1");
    }
    Float shfited = new Float(in.value() / Math.sqrt(1 - Math.pow(in.value(), 2)), in.name() + " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arcsine");
  }

  public static Angle arcsec(Float in, boolean isDegrees) {
    if (in.value() < 1 && in.value() > -1) {
      throw new IllegalArgumentException("Inputs between -1 and 1 are not valid");
    }
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arccos(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arcsecant");
  }

  public static Angle arccsc(Float in, boolean isDegrees) {
    if (in.value() < 1 && in.value() > -1) {
      throw new IllegalArgumentException("Inputs between -1 and 1 are not valid");
    }
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arcsin(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arccosecant");
  }

  public static Angle arccot(Float in, boolean isDegrees) {
    Float shfited = new Float(1 / in.value(), in.name() +  " shifted");
    double val = arctan(shfited, isDegrees).value();
    return new Angle(val, isDegrees, in.name() + " arctangent");
  }

  private static double constrain(double in, boolean isDegrees) {
    double out = 0.0;
    if (isDegrees) {
      out = in % 360.0;
      if (out < 0) {
        out = 360 + out;
      }
    } else {
      out = in % (pi * 2);
      if (out < 0) {
        out = 2 * pi + out;
      }
    }
    return out;
  }
}
