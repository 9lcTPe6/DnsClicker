package ru.appline.ibs.homework.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.ibs.homework.framework.utils.ProductCard;

import java.util.ArrayList;
import java.util.List;

import static ru.appline.ibs.homework.framework.utils.ParserTrimmer.parserTrimmer;

/**
 * Страница продукта
 */
public class ProductPage extends BasePage {

    /**
     * @see ProductPage#productPage - Веб-элемент, содержащий информацию о продукте
     * @see ProductPage#buyButton - кнопка "Купить", добавляющая продукт в корзину
     * @see ProductPage#currentPrice - "чистая" цена, без учета дополнительной гарантии
     * @see ProductPage#additionalGuarantee - строка с выбором дополнительной гарантии
     * @see ProductPage#priceWithGuarantee - цена с учетом дополнительной гарантии
     * @see ProductPage#productRate - рейтинг товара
     * @see ProductPage#defaultGuarantee - гарантия от производителя
     * @see ProductPage#countryBuilder - информация о стране-производителе
     */
    @FindBy(xpath = "//div[@id='product-page']")
    List<WebElement> productPage;

    @FindBy(xpath = "//button[contains(text(), 'Купить')]")
    WebElement buyButton;

    @FindBy(xpath = "//span[@class='product-card-price__current']")
    WebElement currentPrice;

    @FindBy(xpath = "//select[@class='form-control']")
    WebElement additionalGuarantee;

    @FindBy(xpath = "//span[@class='product-card-price__current product-card-price__current_active']")
    WebElement priceWithGuarantee;

    @FindBy(xpath = "//span[@itemprop='ratingValue']")
    WebElement productRate;

    @FindBy(xpath = "//p[@class='item']/span[2]")
    WebElement defaultGuarantee;

    @FindBy(xpath = "//p[@class='item manufacturer-country']/span[2]")
    WebElement countryBuilder;

    /**
     * Коллекция для хранения информации обо всех продуктах, которые мы добавляли в корзину
     */
    static List<ProductCard> productsList = new ArrayList<>();

    /**
     * Переменные для конструктора класса ProductCard
     */
    long clearPrice, newPrice;
    short additionalGuaranteeInfo, defaultGuaranteeInfo;
    String productName, countryBuilderInfo;
    double rateInfo;

    /**
     * Метод для покупки товара и создания его карточки
     * @return CartPage page
     */
    public CartPage buyProduct() {

        for (WebElement element : productPage) {
            /**
             * Общие переменные для всех товаров
             */
            clearPrice = Long.parseLong(parserTrimmer(currentPrice.getText()));
            productName = page.getProductPage().getPageName().substring(7);
            countryBuilderInfo = countryBuilder.getText();
            rateInfo = Double.parseDouble(productRate.getText());

            if (isElementPresent(additionalGuarantee)) {
                action.moveToElement(additionalGuarantee)
                        .click()
                        .build()
                        .perform();
                /**
                 * При необходимости можно сменить на 1, что будет означать дополнительную гарантию на 1 год, а не 2
                 */
                guaranteeOptions(2);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * Переменные для товаров, на которые предоставляется дополнительная гарантия
                 */
                defaultGuaranteeInfo = Short.parseShort(parserTrimmer(defaultGuarantee.getText()));
                newPrice = Long.parseLong(parserTrimmer(priceWithGuarantee.getText()));
            }

            if (isElementPresent(additionalGuarantee)) {
                productsList.add(new ProductCard(productName, clearPrice, newPrice, additionalGuaranteeInfo, rateInfo, defaultGuaranteeInfo, countryBuilderInfo));
            } else {
                productsList.add(new ProductCard(productName, clearPrice, rateInfo, countryBuilderInfo));
            }

            action.moveToElement(buyButton)
                    .click()
                    .build()
                    .perform();
            return page.getCartPage();
        }
        Assertions.fail("Что-то пошло не так...");
        return page.getCartPage();

    }

    /**
     * Оформляем дополнительную гарантию
     * @param amountOfYears - количество лет, на которые предоставляется дополнительная гарантия.
     *                      По умолчанию: 2
     */
    private void guaranteeOptions(int amountOfYears) {

        switch (amountOfYears) {
            case 1:
                action.moveToElement(additionalGuarantee)
                        .sendKeys(Keys.ARROW_DOWN)
                        .sendKeys(Keys.RETURN)
                        .build()
                        .perform();
                additionalGuaranteeInfo = (short) amountOfYears;
                break;
            case 2:
                action.moveToElement(additionalGuarantee)
                        .sendKeys(Keys.ARROW_DOWN)
                        .sendKeys(Keys.ARROW_DOWN)
                        .sendKeys(Keys.RETURN)
                        .build()
                        .perform();
                additionalGuaranteeInfo = (short) amountOfYears;
                break;
        }

    }

}
