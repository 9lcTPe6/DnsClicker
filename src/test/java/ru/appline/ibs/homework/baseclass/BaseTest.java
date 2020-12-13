package ru.appline.ibs.homework.baseclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.appline.ibs.homework.framework.managers.PagesManager;

import static ru.appline.ibs.homework.framework.managers.StartFrameworkManager.startFramework;
import static ru.appline.ibs.homework.framework.managers.StartFrameworkManager.stopFramework;
import static ru.appline.ibs.homework.framework.utils.ConstProps.CHROME_DRIVER;

public class BaseTest {

    protected PagesManager page = PagesManager.getPagesManager();

    @BeforeEach
    public void runTest() {

        startFramework(CHROME_DRIVER);

    }

    @AfterEach
    public void stopTest() {

        stopFramework();

    }
}
