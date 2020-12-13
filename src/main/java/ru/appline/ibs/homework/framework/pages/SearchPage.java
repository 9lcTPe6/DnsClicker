package ru.appline.ibs.homework.framework.pages;

import org.openqa.selenium.Keys;

/**
 * Стартовая страница, с которой начинается поиск
 */
public class SearchPage extends BasePage {

    /**
     * Метод для инициализации поиска
     * @param searchingTarget - Название товара, который нужно найти
     * @return SearchingResultsPage page
     */
    public SearchingResultsPage searchSomething(String searchingTarget) {

        action.moveToElement(searchMenu)
                .click()
                .sendKeys(searchingTarget)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

        return page.getSearchingResultsPage();

    }

}
