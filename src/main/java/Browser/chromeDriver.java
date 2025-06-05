package Browser;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class chromeDriver extends Browser
{
    public WebDriver createDriver()
    {
        return new ChromeDriver();
    }
}
