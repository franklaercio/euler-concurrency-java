import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class EulerUtil {

  private EulerUtil() {
    // Private class isn't instance class
  }

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

  public static BigDecimal calculateEulerNumber(ExecutorService executor, int numberOfTerms, int precision) {
    List<Future<BigDecimal>> results = new ArrayList<>();

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
      throw new EulerException("Could not calculate Euler number", e);
    } finally {
      executor.shutdown();
    }
  }

}
