import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UrlObtainer {
    UrlObtainer(){}
    public static void obtain() throws IOException {
        initialize();
        String url = "https://www.pro-football-reference.com/";
        String players = "players/";
        String teams = "teams/";
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        char letter = 'A';

        for(int i = 0; i<26;i++){
            String urlToSearch = url+players+letter;
            driver.navigate().to(urlToSearch);
            WebElement div_players = driver.findElement(new By.ById("div_players"));
            List<WebElement> playersLinks = div_players.findElements(new By.ByTagName("a"));
            for (WebElement pl : playersLinks){
                FileWriter fw = new FileWriter("urls.txt", true);
                System.out.println("Adding "+pl.getAttribute("href")+" to the urls list");
                fw.write(pl.getAttribute("href")+"\n");
                fw.close();
            }
            letter++;
        }
    }
    public static void delete(){
        File f = new File("urls.txt");
        f.delete();
    }
    public static void renew() throws IOException {
        delete();
        obtain();
    }
    public static void initialize(){
        String os = System.getProperty("os.name");
        if (os.equals("Windows 10")){ System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe"); }
        if (os.contains("Unix")){ System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");}
    }
}
