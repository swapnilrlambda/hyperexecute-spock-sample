import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.By

import static org.junit.jupiter.api.Assertions.assertTrue

class LambdaTestRemoteAutomationTest {

    @Test
    void testLambdaTestAutomation() throws Exception {
        // LambdaTest credentials
        String username = System.getenv("LT_USERNAME")
        String accessKey = System.getenv("LT_ACCESS_KEY")

        assert username != null && accessKey != null : "LambdaTest credentials are not set."

        // LambdaTest remote URL
        String hubURL = "https://${username}:${accessKey}@hub.lambdatest.com/wd/hub"

        // Desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities()
        capabilities.setCapability("browserName", "Chrome")
        capabilities.setCapability("browserVersion", "latest")
        capabilities.setCapability("platform", "Windows 10")

        // LambdaTest options
        Map<String, Object> ltOptions = new HashMap<>()
        ltOptions.put("build", "Maven Remote Test")
        ltOptions.put("name", "Maven LambdaTest Chrome Test")
        ltOptions.put("platformName", "Windows 10")
        ltOptions.put("resolution", "1920x1080")

        capabilities.setCapability("LT:Options", ltOptions)

        WebDriver driver = null
        try {
            driver = new RemoteWebDriver(new URL(hubURL), capabilities)

            // Perform test steps
            driver.get("https://www.lambdatest.com")
            driver.manage().window().maximize()

            def loginButton = driver.findElement(By.cssSelector("a[href*='login']"))
            loginButton.click()

            Thread.sleep(3000)
            assertTrue(driver.getCurrentUrl().contains("login"))
        } finally {
            if (driver != null) {
                driver.quit()
            }
        }
    }
}
