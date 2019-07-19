package project.post_ident;

import org.openqa.selenium.By;
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

        waitForAction(1.0);

        //"Anlegen" aufrufen
        driver.findElement(By.linkText("Anlegen")).click();
        waitForAction(1.0);

        //"Daten ändern" aufrufen
        driver.findElement(By.linkText("Daten ändern")).click();

        //Daten in Formular eintragen
        driver.findElement(By.id("vorname")).sendKeys("Erika\r");
        driver.findElement(By.id("nachname")).sendKeys("Mustermann\r");
        driver.findElement(By.id("persoNr")).sendKeys("T22000129\r");
        driver.findElement(By.id("strasse")).sendKeys("Heidestr.\r");
        driver.findElement(By.id("hausnummer")).sendKeys("17\r");
        driver.findElement(By.id("plz")).sendKeys("50966\r");
        driver.findElement(By.id("stadt")).sendKeys("Köln\r");

        //"Speichern" klicken
        driver.findElement(By.id("datenAendern_speichern")).click();

        waitForAction(1.0);
        driver.close();
    }

}



