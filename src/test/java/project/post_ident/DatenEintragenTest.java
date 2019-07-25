package project.post_ident;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DatenEintragenTest {


    public void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myFirstTest() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\kcoep\\eclipse-workspace\\meinProjekt1\\driver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rapha\\eclipse-workspace\\Test-DHLBonus\\driver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://localhost:8080");

        //Datei auswählen
        driver.findElement(By.id("DateiAuswaehlen")).click();
        waitForAction(2.0);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

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
        waitForAction(2.0);

        //Daten in Formular eintragen
        driver.findElement(By.id("vorname")).clear();
        waitForAction(1.0);
        driver.findElement(By.id("vorname")).sendKeys("OLIVER LUDGER\r");
        waitForAction(2.0);

        driver.findElement(By.id("persoNr")).clear();
        driver.findElement(By.id("persoNr")).sendKeys("L74905TPZ\r");
        waitForAction(1.0);


        //Scroll Page Down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://localhost:8080/datenAendern");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForAction(2.0);

        driver.findElement(By.id("nationalitaet")).clear();
        driver.findElement(By.id("nationalitaet")).sendKeys("DEUTSCH\r");
        waitForAction(1.0);

        driver.findElement(By.id("adresse")).clear();
        driver.findElement(By.id("adresse")).sendKeys("Beispieladresse 2\r");
        waitForAction(2.0);

        driver.findElement(By.id("plz")).clear();
        driver.findElement(By.id("plz")).sendKeys("40203\r");
        waitForAction(2.0);

        //"Speichern" klicken
        driver.findElement(By.id("Speichern")).click();
        waitForAction(2.0);


        driver.findElement(By.id("5one")).click();
        waitForAction(2.0);

        driver.findElement(By.id("comment")).sendKeys("Beste Software ever!");
        waitForAction(2.0);
        driver.findElement(By.id("BewertungAbgeben")).click();
        waitForAction(3.0);

        driver.close();
    }

}




