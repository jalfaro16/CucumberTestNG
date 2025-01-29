//Control Change: last pull 01-29-2025 09:42:00
//Control Change: last commit 01-29-2025 10:00:00

//Control Change: last pull 01-29-2025 10:30:00
//Control Change: last commit 01-29-2025 11:31:00

package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber"
,glue="stepDefinition"
,monochrome=true
,tags = "@Regression"
,plugin= {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
	

}
