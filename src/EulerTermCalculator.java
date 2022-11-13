import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class EulerTermCalculator implements Callable<BigDecimal> {

  private final int factorialNumber;

  private final int precision;

  public EulerTermCalculator(int number, int precision) {
    this.factorialNumber = number;
    this.precision = precision;
  }

  @Override
  public BigDecimal call() {
    BigDecimal factorialTerm = factorial(this.factorialNumber);
    return BigDecimal.ONE.divide(factorialTerm, this.precision, RoundingMode.DOWN);
  }

  public BigDecimal factorial(int number) {
    if (number <= 1) {
      return BigDecimal.ONE;
    } else {
      BigDecimal bn = new BigDecimal(String.valueOf(number));
      return bn.multiply(factorial(number-1));
    }
  }
}
