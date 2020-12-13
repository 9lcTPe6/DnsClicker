package ru.appline.ibs.homework.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.ibs.homework.framework.exceptions.InitDriverException;
import ru.appline.ibs.homework.framework.managers.PagesManager;

import static ru.appline.ibs.homework.framework.managers.WebDriverManager.startDriver;

/**
 * Родительский класс для всех последующих страниц
 */
public class BasePage {

    /**
     * Следующие две переменные типа WebElement отвечают за:
     * @see BasePage#forEquality - переменная для использования в Assertions, когда требуется получить Title страницы
     * @see BasePage#searchMenu - переменная, отвечающая за строку поиска. Общая для всех страниц, поскольку находится
     *                            в тэге <head></head>
     */
    @FindBy(xpath = "//title[text()]")
    WebElement forEquality;

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchMenu;

    protected PagesManager page = PagesManager.getPagesManager();

    /**
     * Инициализация класса Action для эмуляции работы мыши
     */
    protected Actions action;
    {
        try {
            action = new Actions(startDriver());
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация класса JavascriptExecutor для работы со скриптами
     */
    protected JavascriptExecutor js;
    {
        try {
            js = (JavascriptExecutor) startDriver();
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    /**
     * Переменная для работы с ожиданиями
     */
    protected WebDriverWait wait;
    {
        try {
            wait = new WebDriverWait(startDriver(), 20, 1000);
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
    }

    public BasePage() {

        try {
            PageFactory.initElements(startDriver(), this);
        } catch (InitDriverException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод для получения страницы с корзиной. Общий для всех страниц, поскольку так же находится в тэге <head></head>
     * @return CartPage
     */
    public CartPage getCart() {

        WebElement cart = null;
        try {
            cart = startDriver().findElement(By.xpath("//a[@data-commerce-target='CART']"));
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
        action.moveToElement(cart)
                .click()
                .build()
                .perform();
        return page.getCartPage();

    }

    /**
     * Метод для получения страницы поиска. Необходим для отлова моментов, когда страница с результатом поиска отсутствует
     * @return SearchPage
     */
    public SearchPage getSearch() {

        try {
            startDriver().get("https://www.dns-shop.ru/search/");
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
        return page.getSearchPage();

    }

    /**
     * Следующие три метода не потребовались, но оставляю их на случай, если потребуется что-то изменить
     */
    protected void scrollToElementJs(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    protected WebElement elementToBeClickable(WebElement element) {

        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void fillInputField(WebElement field, String value) {

        scrollToElementJs(field);
        elementToBeClickable(field).click();
        field.sendKeys(value);

    }

    /**
     * Метод для получения заголовка страницы
     * @return String pageName
     */
    public String getPageName() {
        String pageName = null;
        try {
             pageName = startDriver().getTitle();
        } catch (InitDriverException e) {
            e.printStackTrace();
        }
        return pageName;
    }

    /**
     * Проверка видимости элемента.
     * @param element
     * @return true - если элемент на странице присутствует
     * @return false - если перехвачено исключение NoSuchElementException, что означает отсутсвие элемента на странице
     */
    public boolean isElementPresent(WebElement element) {
        try {
            element.getLocation();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
