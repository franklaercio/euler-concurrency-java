import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EulerFixedExecutor {

  private static final int NUMBER_OF_PRECISION = 100;

  public static void main(String[] args) {

    try (Scanner keyboard = new Scanner(System.in)) {
      System.out.print("Enter the number of terms to calculate the Euler number: ");
      int numberOfTerms = EulerUtil.parseNumberOfTerms(keyboard.nextLine());

      ExecutorService executor = Executors.newFixedThreadPool(numberOfTerms);

      List<Future<BigDecimal>> results = new ArrayList<>();

      try {
        for (int term = 1; term <= numberOfTerms; term++) {
          Callable<BigDecimal> calculator = new EulerTermCalculator(term, NUMBER_OF_PRECISION);
          Future<BigDecimal> factorial = executor.submit(calculator);
          results.add(factorial);
        }

        BigDecimal eulerNumber = BigDecimal.ONE;

        for (Future<BigDecimal> result : results) {
          eulerNumber = eulerNumber.add(result.get());
        }

        System.out.println("The number of Euler is: " + eulerNumber);
      } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
      } finally {
        executor.shutdown();
      }
    } catch (EulerException e) {
      e.printStackTrace();
    }
  }

}
