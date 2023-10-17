package Runners;

import Utils.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


@CucumberOptions(
        tags = "@UI",
        features = {"src/test/java/FeatureFiles/"},
        glue = {"StepDefinitions"}
)
public class _TestRunnerParallel extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)   // bazı java versiyon hataları için, BROWSER bulamadım hatası verdiğinde msg: Optional olarak ekle
    @Parameters("browser")
    public void beforeClass(@Optional("chrome") String browser) {
        Driver.threadBrowserName.set(browser);
    }

    @AfterClass(alwaysRun = true)    // bazı java versiyon hatalırı için, alternatif browser kapatma
    public void afterClass() {
        Driver.closeDriver();
    }

}
