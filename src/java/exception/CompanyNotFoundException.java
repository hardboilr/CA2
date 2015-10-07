
package exception;

/**
 *
 * @author plaul1
 */
public class CompanyNotFoundException extends Exception {

  public CompanyNotFoundException(String string) {
    super(string);
  }
  public CompanyNotFoundException() {
    super("Company with requested cvr not found");
  }
  
}
