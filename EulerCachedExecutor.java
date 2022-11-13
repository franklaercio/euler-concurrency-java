import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EulerCachedExecutor {

  private static final int NUMBER_OF_PRECISION = 100;

  public static void main(String[] args) {
    try (Scanner keyboard = new Scanner(System.in)) {
      System.out.print("Enter the number of terms to calculate the Euler number: ");
      int numberOfTerms = EulerUtil.parseNumberOfTerms(keyboard.nextLine());

      ExecutorService executor = Executors.newCachedThreadPool();

      BigDecimal eulerNumber = EulerUtil.calculateEulerNumber(executor, numberOfTerms, NUMBER_OF_PRECISION);
      System.out.println("The number of Euler is: " + eulerNumber);
    } catch (EulerException e) {
      e.printStackTrace();
    }
  }
}
