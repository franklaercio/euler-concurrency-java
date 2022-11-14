/**
 * Exception if an error occurs while calculating the Euler's number
 *
 * @see java.lang.RuntimeException
 *
 * @author Frank Laércio
 * @author Ohanna Dezidério
 */
public class EulerException extends RuntimeException {

  /**
   * Constructor to throw new Euler's number error.
   *
   * @param message text to show one message in error.
   * @param cause Throwable all tree error cause.
   */
  public EulerException(String message, Throwable cause) {
    super(message, cause);
  }
}
