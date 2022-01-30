import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Jiménez Encinas
 * @version v1.0
 *
 * <p>Pro football reference american football players data scrapper</p>
 */
public class DataMinerImproved {
    /**
     *
     * @throws IOException on case that can't create or access to file.
     * <p>Obtain data contained in the urls on the urls.txt and generates a csv</p>
     */
    public static void urlsToCsv() throws IOException {
        UrlObtainer.initialize();
        WebDriver driver = new FirefoxDriver();
        BufferedReader fr = new BufferedReader(new FileReader("urls.txt"));
        String fileName = "data.csv";
        FileWriter fwr = new FileWriter(fileName, StandardCharsets.UTF_8);
        CSVWriter csvwr = new CSVWriter(fwr);

        while (true){
            String url = fr.readLine();
            System.out.println(url);
            if (url != null) {
                driver.navigate().to(url);
                List<WebElement> tables = driver.findElements(By.tagName("table"));
                String[] data;
                String name = null;
                List<WebElement> h1s = driver.findElements(By.tagName("h1"));
                for(WebElement h : h1s){
                    if(h.getAttribute("itemprop").equals("name") && h.getAttribute("itemprop") != null){
                        name = h.getText();
                    }
                }
                System.out.println(name);

                ArrayList<String> entries = new ArrayList<String>();
                entries.add(name);
                for(WebElement t : tables){
                    List<WebElement> tr = t.findElements(new By.ByTagName("tr"));
                    for(WebElement d : tr){
                        data = d.getText().split(" ");
                        for(int i = 0; i < data.length; i++){
                            if (data[i] != ""){
                                entries.add(data[i]);
                            }
                        }
                    }
                }
                csvwr.writeNext(entries.toArray(new String[0]));
            }
            else{
                driver.close();
                csvwr.close();
                break;
            }
        }
    }
}
