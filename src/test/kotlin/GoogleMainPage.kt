import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class GoogleMainPage(val driver: WebDriver) {
    companion object {
        const val URL = "https://www.google.com/"

        val SEARCH_FIELD_LOCATION = By.name("q")
    }

    val searchField: WebElement
        get() = driver.findElement(SEARCH_FIELD_LOCATION)
    val searchFieldText: String
        get() = searchField.getAttribute("value")

    fun navigate() {
        driver.navigate().to(URL)
    }

    fun typeToSearchField(query: String) {
        searchField.sendKeys(query)
    }

    fun searchByEnter() {
        searchField.sendKeys(Keys.ENTER)
    }
}