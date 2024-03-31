package Utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.HashMap;

import static StepDefinitions.Hooks.*;

public class Driver {

    public static WebDriver driver;

    private Driver() {
    }

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>(); // chrome , firefox ...

    public static WebDriver getDriver() {

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\target");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        if (isHeadless) {
            chromeOptions.addArguments("use-fake-ui-for-media-stream");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--remote-allow-origins=*");
        } else {
            chromeOptions.addArguments("use-fake-ui-for-media-stream");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--remote-allow-origins=*");
        }

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (isHeadless) {
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--disable-gpu");
            firefoxOptions.addArguments("--window-size=1920,1080");

        } else {
            firefoxOptions.addArguments("--disable-gpu");
            firefoxOptions.addArguments("--window-size=1920,1080");

        }

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--window-size=1920,1080");
        edgeOptions.addArguments("use-fake-ui-for-media-stream");

        // paralel çalışmayan yani XML browser parametresiyle gelmediği durumlarda
        // tanımlı browser değeri Configuration.properties'den alınıyor
        if (threadBrowserName.get() == null){
            browserType = ConfigurationReader.getProperty("browser");
            threadBrowserName.set(browserType);                         // Browser ismi atanıyor
        }

        // Her bir iş parçacğığının sorunsuz ve ayrı çalıştırılabilmesi için
        synchronized (Driver.class) {
            if (threadDriver.get() == null) {
                browserType = threadBrowserName.get();          // Browser ismi atanıyor

                switch (browserType) {

                    case "firefox":
                        threadDriver.set(new FirefoxDriver(firefoxOptions));
                        break;

                    case "ie":
                        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                            throw new WebDriverException("Your OS doesn't support Internet Explorer");
                        threadDriver.set(new InternetExplorerDriver());
                        break;

                    case "edge":
                        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                            throw new WebDriverException("Your OS doesn't support Edge");
                        threadDriver.set(new EdgeDriver(edgeOptions));
                        break;

                    case "safari":
                        if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                            throw new WebDriverException("Your OS doesn't support Safari");
//                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        threadDriver.set(new SafariDriver());
                        break;

                    default:               // Diğer tüm durumlarda chrome geçerli sayıldı
                        threadDriver.set(new ChromeDriver(chromeOptions));
                        break;
//                        WebDriverManager.chromedriver().setup();
                }

                if (isFullScreen) {
                    threadDriver.get().manage().window().maximize();
                } else
                {
                    Dimension dimension = new Dimension(width, height);
                    threadDriver.get().manage().window().setSize(dimension);
                }
                threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            }
        }

        return threadDriver.get();
    }

    public static void closeDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            threadDriver.remove();
        }
    }
}

