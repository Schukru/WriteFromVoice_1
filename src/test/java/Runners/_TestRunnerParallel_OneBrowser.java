package Runners;

import Utils.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


@CucumberOptions(
        tags = "@Regression",
        features = {"src/test/java/FeatureFiles/"},
        glue = {"StepDefinitions"}
)
public class _TestRunnerParallel_OneBrowser extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)   // bazı java versiyon hataları için, BROWSER bulamadım hatası verdiğinde msg: Optional olarak ekle
    public void beforeClass() {
    }

    @AfterClass(alwaysRun = true)    // bazı java versiyon hatalırı için, alternatif browser kapatma
    public void afterClass() {
        Driver.closeDriver();
    }

}
