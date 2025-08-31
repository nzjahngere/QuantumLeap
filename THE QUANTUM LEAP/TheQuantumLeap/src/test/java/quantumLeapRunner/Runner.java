package quantumLeapRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {//"E:\\\\\\\\STS\\\\\\\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\\\\\\\TheQuantumLeap\\\\\\\\QuantumLeap Features\\\\\\\\Login.feature",
				//"E:\\\\\\\\\\\\\\\\STS\\\\\\\\\\\\\\\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\\\\\\\\\\\\\\\TheQuantumLeap\\\\\\\\\\\\\\\\QuantumLeap Features\\\\\\\\\\\\\\\\Homepage.feature",
				//"E:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\STS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TheQuantumLeap\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\QuantumLeap Features\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Cart.feature",
				"E:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\STS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TheQuantumLeap\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\QuantumLeap Features\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Checkout.feature"},
		
		glue = {"quantumLeapTests"}
		)

public class Runner extends AbstractTestNGCucumberTests {

}
