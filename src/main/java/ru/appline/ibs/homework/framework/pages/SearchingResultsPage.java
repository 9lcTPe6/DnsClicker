package ru.appline.ibs.homework.framework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Страница с результатами поиска
 */
public class SearchingResultsPage extends BasePage {

    /**
     * @see SearchingResultsPage#searchResults - Веб-элемент, содержащий результат поиска
     * @see SearchingResultsPage#linkElement - Элемент, содержащий ссылку на товар. Используется для перехода к искомому продукту
     */
    @FindBy(xpath = "//div[@data-role='catalog-items']")
    List<WebElement> searchResults;

    @FindBy(xpath = "//a[@data-role='clamped-link']")
    WebElement linkElement;

    /**
     * Получение результатов поиска
     * @param productName - Название товара, который мы искали
     * @return ProductPage page
     */
    public ProductPage getSearchResults(String productName) {

        /**
         * Проверка, не является ли страница поиска пустой
         */
        if (!forEquality.getText().contains("Результаты поиска")) {
            for (WebElement element : searchResults) {
                if (element.getText().contains(productName)) {
                    action.moveToElement(element)
                            .click(linkElement)
                            .build()
                            .perform();
                    return page.getProductPage();
                }
            }
        } else {
            action.moveToElement(searchMenu)
                    .click()
                    .sendKeys(productName)
                    .sendKeys(Keys.RETURN)
                    .build()
                    .perform();
        }
        return page.getProductPage();

    }

}
