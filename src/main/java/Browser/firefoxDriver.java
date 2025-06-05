package Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class firefoxDriver extends Browser
{
    public WebDriver createDriver()
    {
        return new FirefoxDriver();
    }
}
