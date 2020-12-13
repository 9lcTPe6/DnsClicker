package ru.appline.ibs.homework.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.ibs.homework.framework.utils.ProductCard;

import java.util.List;

import static ru.appline.ibs.homework.framework.pages.ProductPage.productsList;
import static ru.appline.ibs.homework.framework.utils.ParserTrimmer.parserTrimmer;

/**
 * Страница корзины покупок
 */
public class CartPage extends BasePage {

    /**
     * @see CartPage#productCardInfo - элемент, содержащий информацию о продукте. Может быть несколько на странице
     * @see CartPage#totalPrice - общая сумма покупок. Не учитывает стоимость дополнительной гарантии
     * @see CartPage#buyButton - кнопка "Оформить заказ"
     * @see CartPage#removeButton - кнопка "Удалить", для удаления продукта из корзины
     * @see CartPage#addOne - кнопка "+" для добавления ещё одного такого же продукта в корзину
     * @see CartPage#removeOne - кнопка "-" для удаления добавленного продукта из корзины
     * @see CartPage#additionalGuarantees - строка с селекторами выбора дополнительной гарантии
     * @see CartPage#restoreLastRemoved - элемент, содержащий кнопку "Восстановить удалённое"
     * @see CartPage#restoreLast - непосредственно сама кнопка "Восстановить удалённое"
     */
    @FindBy(xpath = "//div[@data-cart-product-id]")
    List<WebElement> productCardInfo;

    @FindBy(xpath = "//div[@class='total-amount__label']")
    List<WebElement> totalPrice;

    @FindBy(xpath = "//button[@class='buy-button']")
    WebElement buyButton;

    @FindBy(xpath = "//button[contains(text(), 'Удалить')]")
    WebElement removeButton;

    @FindBy(xpath = "//button[@data-commerce-action='CART_ADD']")
    WebElement addOne;

    @FindBy(xpath = "//button[@data-commerce-action='CART_REMOVE']")
    WebElement removeOne;

    @FindBy(xpath = "//span[@class='additional-warranties-row__price']")
    WebElement additionalGuarantees;

    @FindBy(xpath = "//div[@class='cart-page-new__item cart-page-new__item_desktop']")
    WebElement restoreLastRemoved;

    @FindBy(xpath = "//span[@class='restore-last-removed']")
    WebElement restoreLast;

    /**
     * Метод для удаления продукта из корзины
     * @param productName - Название продукта, который нужно удалить
     * @return CartPage this
     */
    public CartPage removeProduct(String productName) {

        for (WebElement element : productCardInfo) {
            if (element.getText().toLowerCase().contains(productName.toLowerCase())) {
                action.moveToElement(element)
                        .click(removeButton)
                        .build()
                        .perform();
                return this;
            }
        }

        return this;

    }

    /**
     * Возвращает удалённый из корзины продукт
     * @return CartPage this
     */
    public CartPage restoreLastRemoved() {

        if (restoreLastRemoved.isDisplayed()) {
            action.moveToElement(restoreLastRemoved)
                    .click(restoreLast)
                    .build()
                    .perform();
            return this;
        }
        return this;

    }

    /**
     * Метод для добавления такого же типа продукта в корзину
     * @param amount - количество, которое хотим добавить
     * @param productName - название продукта, который имеется в корзине и количество которого нужно увеличить
     * @return CartPage this
     */
    public CartPage addProduct(int amount, String productName) {

        for (WebElement element : productCardInfo) {
            if (element.getText().toLowerCase().contains(productName.toLowerCase())) {
                for (int i = 0; i < amount; i++) {
                    action.moveToElement(element)
                            .click(addOne)
                            .build()
                            .perform();
                }
                return this;
            }
            Assertions.fail("Искомый продукт отсутсвует в корзине.");
            return this;
        }
        return this;
    }

    /**
     * Метод для удаления необходимого количества продуктов. Обратный методу addProduct
     * @param amount - количество продуктов, которые нужно удалить
     * @param productName - название продукта, который имеется в корзине
     * @return CartPage this
     */
    public CartPage removeSome(int amount, String productName) {

        for (WebElement element : productCardInfo) {
            if (element.getText().toLowerCase().contains(productName.toLowerCase())) {
                for (int i = 0; i < amount; i++) {
                    action.moveToElement(element)
                            .click(removeOne)
                            .build()
                            .perform();
                }
                return this;
            }
            Assertions.fail("Искомый продукт отсутсвует в корзине.");
            return this;
        }
        return this;

    }

    /**
     * Метод для перехода к оформлению заказа
     * @return SearchPage page
     */
    public SearchPage buyAll() {

        action.moveToElement(buyButton)
                .click()
                .build()
                .perform();
        return page.getSearchPage();

    }

    /**
     * Метод для проверки общей суммы покупок, без учета дополнительной гарантии
     * @return CartPage this
     */
    public CartPage checkSummaryPrice() {

        long sum = 0;
        for (ProductCard cards : productsList) {
            sum += cards.getPrice();
        }

        for (WebElement element : totalPrice) {
            if (Long.parseLong(parserTrimmer(element.getText())) != sum) {
                Assertions.fail("Сумма покупок не соответствует сумме к оплате.");
                return this;
            }
        }

        return this;

    }

    /**
     * Метод для проверки суммы покупок с учетом всех дополнительных гарантий
     * @return CartPage this
     * P.S. Нужно будет доработать, поскольку когда писал данное пояснение, понял, что сумма может учитываться неправильно,
     *      если количество продуктов одного типа больше 1.
     */
    public CartPage checkSummaryPriceWithGuarantee() {

        long sum = 0;
        long warr = 0;
        for (ProductCard cards : productsList) {
            sum += cards.getPrice();
            if (cards.getPriceWithGuarantee() != cards.getPrice()) {
                sum += cards.getPriceWithGuarantee();
            }
            for (WebElement element : productCardInfo) {
                String sWarranty = String.format("/..//div[@data-commerce-target='basket_additional_warranty_%s']", ((cards.getAdditionalGuarantee() * 12)));
                WebElement warranty = element.findElement(By.xpath(additionalGuarantees.toString() + sWarranty));
                try {
                    warr = Long.parseLong(parserTrimmer(warranty.findElement(By.xpath("//span[@class='additional-warranties-row__price']")).getText()));
                } catch (NoSuchElementException e) {
                    break;
                }
            }
        }

        for (WebElement element : totalPrice) {
            if ((Long.parseLong(parserTrimmer(element.getText())) + warr) != sum) {
                Assertions.fail("Сумма покупок и дополнительной гарантии не соответствует сумме к оплате.");
                return this;
            }
        }
        return this;

    }

    /**
     * Метод для проверки, отмечена ли дополнительная гарантия
     * @return CartPage this
     */
    public CartPage checkGuarantee() {

        for (ProductCard cards : productsList) {
            for (WebElement element : productCardInfo) {
                if (element.findElement(By.xpath("//div[@class='additional-warranties-row cart-items__additionals-item']")).isDisplayed()) {
                    String sWarranty = String.format("/..//div[@data-commerce-target='basket_additional_warranty_%s']", ((cards.getAdditionalGuarantee() * 12)));
                    // На этой строке появляется ошибка InvalidSelectorException:
                    // SyntaxError: Failed to execute 'evaluate' on 'Document': The string '[[ChromeDriver: chrome on WINDOWS (f84575603184eafaa2456dd315a22d0d)]
                    // -> xpath: //span[@class='additional-warranties-row__price']]/..//div[@data-commerce-target='basket_additional_warranty_24']' is not a valid XPath expression.
                    // Всё из-за скобки                                        здесь
                    // Не могу понять, откуда она появляется, возможно из-за format.
                    WebElement warranty = element.findElement(By.xpath(additionalGuarantees.toString() + sWarranty));
                    try {
                        WebElement warr = warranty.findElement(By.xpath("//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']"));
                        return this;
                    } catch (NoSuchElementException e) {
                        Assertions.fail("Дополнительная гарантия не выбрана.");
                        return this;
                    }
                }
            }
        }
        return this;

    }

    /**
     * Метод для проверки наличия продукта в корзине
     * @param productName - название продукта, который хотим проверить на наличие в корзине
     * @return CartPage this
     * P.S. Возможно, стоит изменить тип на boolean, чтобы не ронять тест
     */
    public CartPage checkCartForProduct(String productName) {

        for (ProductCard cards : productsList) {
            if (cards.getName().toLowerCase().contains(productName.toLowerCase())) {
                for (WebElement element : productCardInfo) {
                    if (element.getText().equalsIgnoreCase(cards.getName())) {
                        return this;
                    }
                }
            } else {
                Assertions.fail("Продукт не найден в корзине.");
                return this;
            }
        }

        return this;

    }

    /**
     * Метод для начала нового поиска
     * @return SearchPage page
     */
    public SearchPage startNewSearch() {
        return page.getSearchPage();
    }

}
