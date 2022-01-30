import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMiner {
    public static void urlsToCsv() throws IOException {
        UrlObtainer.initialize();
        WebDriver driver = new FirefoxDriver();
        BufferedReader fr = new BufferedReader(new FileReader("urls.txt"));
        while (true){
            String url = fr.readLine();
            System.out.println(url);
            if (url != null) {
                driver.navigate().to(url);

                try {
                    List<WebElement> divs = driver.findElements(new By.ByTagName("div"));
                    StringBuilder dataDiv = new StringBuilder();
                    for (WebElement d : divs) {
                        if (d.getAttribute("itemtype") != null && d.getAttribute("itemtype").equals("https://schema.org/Person")) {
                            dataDiv.append(d.getText());
                        }
                    }
                    String[] data = dataDiv.toString().split("\n");
                    for (int i = 1; i < data.length; i++) {
                        if (i == data.length - 1) {
                            System.out.print(data[i] + "\n");
                        } else {
                            System.out.print(data[i] + ", ");
                        }

                    }
                    //System.out.println('"'+name+'"'+", "+position);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                driver.close();
                break;
            }
        }
    }
}
