package ru.appline.ibs.homework.framework.managers;

import ru.appline.ibs.homework.framework.pages.CartPage;
import ru.appline.ibs.homework.framework.pages.ProductPage;
import ru.appline.ibs.homework.framework.pages.SearchingResultsPage;
import ru.appline.ibs.homework.framework.pages.SearchPage;

/**
 * Менеджер для работы со страницами
 */
public class PagesManager {

    private static PagesManager pagesManager;

    CartPage cartPage;
    ProductPage productPage;
    SearchingResultsPage searchingResultsPage;
    SearchPage searchPage;

    private PagesManager() {

    }

    public static PagesManager getPagesManager() {

        if (pagesManager == null) {
            pagesManager = new PagesManager();
        }
        return pagesManager;

    }

    public CartPage getCartPage() {

        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;

    }

    public ProductPage getProductPage() {

        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;

    }

    public SearchingResultsPage getSearchingResultsPage() {

        if (searchingResultsPage == null) {
            searchingResultsPage = new SearchingResultsPage();
        }
        return searchingResultsPage;

    }

    public SearchPage getSearchPage() {

        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;

    }

}
