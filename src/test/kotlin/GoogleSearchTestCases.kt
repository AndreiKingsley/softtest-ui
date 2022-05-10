import org.openqa.selenium.By
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class GoogleSearchTestCases {
    @Test
    fun simpleSearch() {
        val query = "horse"
        withDriver {
            with(GoogleMainPage(this)) {
                navigate()
                assertEquals(driver.currentUrl, "https://www.google.com/")
                typeToSearchField(query)
                assertEquals(searchFieldText, query)
                searchByEnter()
                assertTrue(
                    driver.currentUrl.startsWith(
                        "https://www.google.com/search?q=$query"
                    )
                )
            }
        }
    }

    @Test
    fun searchResult() {
        val query = "tiktok"
        withDriver {
            with(GoogleMainPage(this)) {
                navigate()
                typeToSearchField(query)
                searchByEnter()
                assertEquals(searchFieldText, query)
                driver.findElements(By.tagName("h3")).any {
                    it.text.lowercase().contains(query)
                }
            }
        }
    }

    @Test
    fun tooltips() {
        val query = "carrot"
        withDriver {
            with(GoogleMainPage(this)) {
                navigate()
                typeToSearchField(query)
                Thread.sleep(5000)
                val tooltipTexts = driver.findElement(By.tagName("form"))
                    .findElements(By.tagName("span")).map {
                        it.text
                    }
                assertTrue(tooltipTexts.any { it.lowercase().contains(query) })
            }
        }
    }

    @Test
    fun clearButton() {
        val query = "softest"
        withDriver {
            with(GoogleMainPage(this)) {
                navigate()
                typeToSearchField(query)
                val clearButton = driver.findElement(By.tagName("form"))
                    .findElement(By.cssSelector("[role=\"button\"]"))
                clearButton.click()
                assertTrue(searchFieldText.isBlank())
            }
        }
    }

    @Test
    fun searchResultImages() {
        val query = "minecraft images"
        withDriver {
            with(GoogleMainPage(this)) {
                navigate()
                typeToSearchField(query)
                searchByEnter()
                Thread.sleep(5000)
                assertTrue(driver.findElements(By.tagName("img")).size > 5)
            }
        }
    }
}