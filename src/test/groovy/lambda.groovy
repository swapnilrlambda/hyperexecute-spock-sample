import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.By

class LambdaTestRemoteAutomation {
    static void main(String[] args) {
        // LambdaTest credentials
        String username = "linpaws13"  // Replace with your LambdaTest username
        String accessKey = "7HzTZNYQr2ODUvr3XO4iXdVfQPRRZcYIEeRiGsQxD9Ndva763B"  // Replace with your LambdaTest access key

        // LambdaTest remote URL
        String hubURL = "https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"

        // Desired capabilities for the browser (Chrome in this case)
        DesiredCapabilities capabilities = new DesiredCapabilities()
        capabilities.setCapability("browserName", "Chrome")
        capabilities.setCapability("browserVersion", "latest")
        capabilities.setCapability("platform", "Windows 10")  // Platform can be adjusted based on your requirements

        // Optional capabilities for LambdaTest
        Map<String, Object> ltOptions = new HashMap<>()
        ltOptions.put("build", "Groovy Remote Test")
        ltOptions.put("name", "Groovy LambdaTest Chrome Test")
        ltOptions.put("platformName", "Windows 10")
        ltOptions.put("resolution", "1920x1080")

        capabilities.setCapability("LT:Options", ltOptions)

        // Initialize RemoteWebDriver for LambdaTest
        WebDriver driver = new RemoteWebDriver(new URL(hubURL), capabilities)

        try {
            // Navigate to LambdaTest homepage
            driver.get("https://www.lambdatest.com")

            // Maximize the browser window
            driver.manage().window().maximize()

            // Find the "Login" button by its CSS Selector and click on it
            def loginButton = driver.findElement(By.cssSelector("a[href*='login']"))
            loginButton.click()

            // Optionally, wait for a few seconds to visually verify the login page is opened
            Thread.sleep(3000)
        } finally {
            // Close the browser
            driver.quit()
        }
    }
}
