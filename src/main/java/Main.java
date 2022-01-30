import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/**
 * @author Carlos Jiménez Encinas
 * @version v1.0
 *
 * <p>Pro football reference american football players data scrapper</p>
 */
public class Main {
  /**
   *
   * @param args
   * @throws IOException
   * <p>Typical main entry class of java program.</p>
   */
  public static void main(String[] args) throws IOException {
    //UrlObtainer.renew();
    //Convert the data obtained to csv.
    DataMinerImproved.urlsToCsv();
  }
}

