
// dealing mathematical calculations from Maths class
public final class Maths {
  public static double invertSq(double var) {
    final double xValue = 0.5d*var;
    long i = Double.doubleToLongBits(var);
    i = 0x5fe6eb50c7b537a9L - (i >> 1);
    var = Double.longBitsToDouble(i);
    var *= (1.5d - xValue*var*var); 
    var *= (1.5d - xValue*var*var); 
    var *= (1.5d - xValue*var*var); 
    var *= (1.5d - xValue*var*var);
    return var;
  }

  public static final double sqrt(final double value) {
    return Math.sqrt(value);
  }
}
