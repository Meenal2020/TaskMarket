package com.kodekonveyor.market.integrationtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumTestHelper {

  private static FirefoxDriver driver;

  static {
    final FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--headless");
    driver = new FirefoxDriver(firefoxOptions);
  }

  public static void close() {
    driver.close();
  }

  public static FirefoxDriver getDriver() {
    return driver;
  }

  public static WebElement waitFor(final String cssSelector) {
    final WebDriverWait wait = new WebDriverWait(driver, 20);
    return wait.until(
        ExpectedConditions.elementToBeClickable(
            By.cssSelector(cssSelector)
        )
    );
  }

}
