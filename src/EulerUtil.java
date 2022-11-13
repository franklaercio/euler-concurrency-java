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
      throw new EulerException("The Euler number isn't valid, verify if is numeric and greater than 0", e);
    }
  }

}
