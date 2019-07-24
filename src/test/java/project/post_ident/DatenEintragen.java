package project.post_ident;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DatenEintragen {


    public void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myFirstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\krach\\eclipse-workspace\\Testautomation-DHL-Bonusprogramm\\driver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://localhost:8080");

        //Datei auswählen
     /*   driver.findElement(By.id("DateiAuswaehlen")).click();
        waitForAction(2.0);*/
        String homedirectory=System.getProperty("user.home");
        String fullpath=homedirectory+"\\Downloads\\ausweisOlli.png";
        driver.findElement(By.id("DateiAuswaehlen")).sendKeys(fullpath);
        waitForAction(2.0);

        //"Anlegen" aufrufen
        driver.findElement(By.id("Anlegen")).click();
        waitForAction(3.0);


    /*    driver.findElement(By.linkText("Anlegen")).click();
        waitForAction(1.0);*/

        //"Daten ändern" aufrufen
        driver.findElement(By.linkText("Daten ändern")).click();
        waitForAction(3.0);

        //Daten in Formular eintragen
        driver.findElement(By.id("vorname")).clear();
        waitForAction(1.0);
        driver.findElement(By.id("vorname")).sendKeys("Oliver Ludger\r");
        waitForAction(1.0);

        driver.findElement(By.id("persoNr")).clear();
        driver.findElement(By.id("persoNr")).sendKeys("L74905TPZ\r");
        waitForAction(1.0);

        driver.findElement(By.id("strasse")).clear();
        driver.findElement(By.id("strasse")).sendKeys("Beispielstr.\r");
        waitForAction(1.0);

        driver.findElement(By.id("hausnummer")).clear();
        driver.findElement(By.id("hausnummer")).sendKeys("17\r");
        waitForAction(1.0);


        //Scroll Page Down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://localhost:8080/datenAendern");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForAction(1.0);

        driver.findElement(By.id("plz")).clear();
        driver.findElement(By.id("plz")).sendKeys("40203\r");
        waitForAction(1.0);

        driver.findElement(By.id("stadt")).clear();
        driver.findElement(By.id("stadt")).sendKeys("Hamburg\r");

        //"Speichern" klicken
        driver.findElement(By.id("Speichern")).click();
        waitForAction(2.0);


        driver.findElement(By.id("5one")).click();
        waitForAction(1.0);

        driver.findElement(By.id("comment")).sendKeys("Beste Software ever!");
        waitForAction(2.0);
        driver.findElement(By.id("BewertungAbgeben")).click();
        waitForAction(5.0);

        driver.close();
    }

}



