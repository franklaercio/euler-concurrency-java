import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * The Util class to calculate Euler Number
 *
 * @see java.util.concurrent.ExecutorService
 * @see java.util.concurrent.Callable
 * @see java.util.concurrent.Future
 *
 * @author Frank Laércio
 * @author Ohanna Dezidério
 */
public class EulerUtil {

  /**
   * Classes utilities cannot be called
   */
  private EulerUtil() {
    // Private class isn't instance class
  }

  /**
   * Method responsible for converting the number of terms from the Euler number.
   *
   * @param number it's a numbers of terms to calculate.
   * @return the number of euler terms to calculate.
   * @throws EulerException happens when the string is not a number or when it is less than 1.
   */
  public static int parseNumberOfTerms(String number) {
    try {
      int numberParsed = Integer.parseInt(number);

      if (numberParsed < 1) {
        throw new NumberFormatException("The number entered is less than 0");
      }

      return numberParsed;
    } catch (NumberFormatException e) {
      throw new EulerException("The Euler number isn't valid", e);
    }
  }

  /**
   * Method responsible for calculate the Euler's number. <br>
   * It's calculate by concurrency strategy. <br>
   * It's used ArrayList for save the all results in the 1 divide by exponential term.
   *
   * @param executor interface in Java to controller threads executions.
   * @param numberOfTerms number of terms to calculate Euler's number.
   * @param precision number of precision in divide calculation.
   * @return The Euler's number in BigDecimal value.
   * @throws EulerException happens when the string is not a number or when it is less than 1.
   */
  public static BigDecimal calculateEulerNumber(ExecutorService executor, int numberOfTerms, int precision) {
    List<Future<BigDecimal>> results = Collections.synchronizedList(new ArrayList<>());

    try {
      for (int term = 1; term <= numberOfTerms; term++) {
        Callable<BigDecimal> calculator = new EulerTermCalculator(term, precision);
        Future<BigDecimal> factorial = executor.submit(calculator);
        results.add(factorial);
      }

      BigDecimal eulerNumber = BigDecimal.ONE;

      for (Future<BigDecimal> result : results) {
        eulerNumber = eulerNumber.add(result.get());
      }

      return eulerNumber;
    } catch (ExecutionException | InterruptedException e) {
      throw new EulerException("Unable to calculate Euler number", e);
    } finally {
      executor.shutdown();
    }
  }

}
