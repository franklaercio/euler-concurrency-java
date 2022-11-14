import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

/**
 * The fixed thread poll to calculate one term of Euler's number. <br>
 *
 *
 * @see java.util.concurrent.Callable
 *
 * @author Frank Laércio
 * @author Ohanna Dezidério
 */
public class EulerTermCalculator implements Callable<BigDecimal> {

  private final int factorialNumber;

  private final int precision;

  /**
   * @param number one int Euler's term.
   * @param precision number of decimal case.
   */
  public EulerTermCalculator(int number, int precision) {
    this.factorialNumber = number;
    this.precision = precision;
  }

  @Override
  public BigDecimal call() {
    BigDecimal factorialTerm = factorial(this.factorialNumber);
    return BigDecimal.ONE.divide(factorialTerm, this.precision, RoundingMode.DOWN);
  }

  /**
   * Method responsible for calculate factorial number recursively.
   *
   * @param number it's a factorial number to calculate.
   * @return factorial number.
   */
  public BigDecimal factorial(int number) {
    if (number <= 1) {
      return BigDecimal.ONE;
    } else {
      BigDecimal bn = new BigDecimal(String.valueOf(number));
      return bn.multiply(factorial(number-1));
    }
  }
}
