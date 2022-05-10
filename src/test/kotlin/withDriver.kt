import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

fun withDriver(block : WebDriver.() -> Unit) {
    SafariDriver().apply {
        block()
        quit()
    }
}
