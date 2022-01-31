import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Carlos Jiménez Encinas
 * @version v1.0
 *
 * <p>Pro football reference american football players url obtainer and manager</p>
 */
public class UrlObtainer {
    /**
     *
     * @throws IOException on case that can't create or access to file.
     * <p>Obtain all players urls</p>
     */
    public static void obtain() throws IOException {
        //Initialize to put properties depends on your os.
        initialize();
        //Set the main url string constant.
        final String url = "https://www.pro-football-reference.com/";
        //Set the players url snippet string constant.
        final String players = "players/";
        //Set the teams url snippet string constant (not implemented part).
        final String teams = "teams/";
        //Initializing web driver to use Firefox.
        WebDriver driver = new FirefoxDriver();
        //Opening the main url.
        driver.get(url);
        //Creating a char variable with the letter to find players.
        char letter = 'A';
        //For every letter in the abecedary.
        for(int i = 0; i<26;i++){
            //Url with letter changed each iteration.
            String urlToSearch = url+players+letter;
            //Navigation to the url.
            driver.navigate().to(urlToSearch);
            //Obtaining all elements with the id "div_players".
            WebElement div_players = driver.findElement(new By.ById("div_players"));
            //Creating a list ith all links inside the WebElement with the id "div_players".
            List<WebElement> playersLinks = div_players.findElements(new By.ByTagName("a"));
            //For each element in the previous list.
            for (WebElement pl : playersLinks){
                //Initialize a FileWriter with append set to true.
                FileWriter fw = new FileWriter("urls.txt", true);
                //Printing the link to be added to file.
                System.out.println("Adding "+pl.getAttribute("href")+" to the urls list");
                //And add the href attribute to the file.
                fw.write(pl.getAttribute("href")+"\n");
                //Close the file access.
                fw.close();
            }
            //Incrementing letter.
            letter++;
        }
    }

    /**
     * <p>Deletes the urls.txt file</p>
     */
    public static void delete(){
        //Initializing a File object with urls.txt as a file.
        File f = new File("urls.txt");
        //Deleting the file urls.txt
        f.delete();
    }
    /**
     * <p>Executes delete() and obtain() functions</p>
     */
    public static void renew() throws IOException {
        //delete() and obtain() in the same function.
        delete();
        obtain();
    }
    /**
     * <p>Puts properties depends of the os running this</p>
     */
    public static void initialize(){
        //Getting the os name into string constant.
        final String os = System.getProperty("os.name");
        //In case the os is Windows 10. we set the .exe binary of the driver to use.
        if (os.equals("Windows 10")){ System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe"); }
        //In case the os is Unix-based. we set the unix binary of the driver to use.
        else (os.contains("Linux")){ System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");}
    }
}
