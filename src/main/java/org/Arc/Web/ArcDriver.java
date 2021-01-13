package org.Arc.Web;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class ArcDriver extends ChromeDriver {
    private Website PAGE;
    public ArcDriver() {
        super(init());
        PAGE = null;
    }

    public ArcDriver(Website website) {
        this();
        PAGE = website;
        loadPage(PAGE);
    }
    
    private final static ChromeOptions init() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //"--disable-gpu", --headless
        options.addArguments("--start-maximized", "--ignore-certificate-errors", "--disable-infobars");
        return options;
    }

    public void loadPage(Website website) {
        get(website.toLink());
    }
    
    public Website getPage(){
        return PAGE;
    }
}
