package ru.appline.ibs.homework.tests;

import org.junit.jupiter.api.Test;
import ru.appline.ibs.homework.baseclass.BaseTest;

public class RunTest extends BaseTest {

    @Test
    public void startTest() {
        page.getSearchPage()
                .searchSomething("Playstation 4")
                .getSearchResults("Игровая консоль PlayStation 4")
                .buyProduct()
                .getSearch()
                .searchSomething("Detroit")
                .getSearchResults("Detroit")
                .buyProduct()
                .getCart()
                .checkGuarantee()
                .checkSummaryPriceWithGuarantee()
                .removeProduct("Detroit")
                .checkSummaryPriceWithGuarantee()
                .addProduct(2,"PlayStation 4")
                .checkSummaryPriceWithGuarantee()
                .restoreLastRemoved()
                .checkCartForProduct("Detroit");
    }

}
