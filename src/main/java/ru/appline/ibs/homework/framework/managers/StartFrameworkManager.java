package ru.appline.ibs.homework.framework.managers;

import ru.appline.ibs.homework.framework.exceptions.InitDriverException;
import ru.appline.ibs.homework.framework.utils.ConstProps;

import java.util.concurrent.TimeUnit;

import static ru.appline.ibs.homework.framework.managers.WebDriverManager.*;

/**
 * Менеджер, отвечающий за запуск фреймворка
 */
public class StartFrameworkManager {

    private static PropertyManager properties = PropertyManager.getPropertyManager();

    /**
     * Метод принимает на вход переменную из Enum ConstProps, отвечающую за драйвер, который планируется использовать
     * @see ConstProps#CHROME_DRIVER
     * @see ConstProps#FIREFOX_DRIVER
     * @param CONST_PROP
     */
    public static void startFramework(ConstProps CONST_PROP) {

        initDriver(CONST_PROP);
        try {
            startDriver().manage().window().maximize();
            startDriver().manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty(ConstProps.IMPLICITLY_WAIT.getConstantProp())), TimeUnit.SECONDS);
            startDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty(ConstProps.PAGELOAD_WAIT.getConstantProp())), TimeUnit.SECONDS);
            startDriver().manage().timeouts().setScriptTimeout(Long.parseLong(properties.getProperty(ConstProps.SCRIPT_WAIT.getConstantProp())), TimeUnit.SECONDS);
            startDriver().get(properties.getProperty(ConstProps.TEST_URL.getConstantProp()));
        } catch (InitDriverException e) {
            e.printStackTrace();
        }

    }

    /**
     * Завершение работы фреймворка
     */
    public static void stopFramework() {

        shutDown();

    }

}
