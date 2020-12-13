package ru.appline.ibs.homework.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.appline.ibs.homework.framework.exceptions.InitDriverException;
import ru.appline.ibs.homework.framework.utils.ConstProps;

import static ru.appline.ibs.homework.framework.utils.ConstProps.CHROME_DRIVER;
import static ru.appline.ibs.homework.framework.utils.ConstProps.FIREFOX_DRIVER;

/**
 * Менеджер, отвечающий за переменные WebDriver и сам WebDriver
 */

public class WebDriverManager {

    private static WebDriver driver;

    private static PropertyManager properties = PropertyManager.getPropertyManager();

    /**
     *  Конструктор принимает значение из метода initDriver
     * @see ConstProps#CHROME_DRIVER
     * @see ConstProps#FIREFOX_DRIVER
     * @param CONST_PROP
     */
    private WebDriverManager(ConstProps CONST_PROP) {

        switch (CONST_PROP) {
            case CHROME_DRIVER:
                System.setProperty("webdriver.chrome.driver", properties.getProperty(CHROME_DRIVER.getConstantProp()));
                driver = new ChromeDriver();
                break;
            case FIREFOX_DRIVER:
                System.setProperty("webdriver.gecko.driver", properties.getProperty(FIREFOX_DRIVER.getConstantProp()));
                driver = new FirefoxDriver();
                break;
        }

    }

    /**
     * Метод для инициализации драйвера. Принимает значения из Enum класса ConstProps
     * @see ConstProps#CHROME_DRIVER
     * @see ConstProps#FIREFOX_DRIVER
     * @param CONST_PROP
     */
    public static void initDriver(ConstProps CONST_PROP) {

        if (driver == null) {
            new WebDriverManager(CONST_PROP);
        }

    }

    /**
     * Метод для запуска и работы с драйвером
     * @return WebDriver driver
     * @throws InitDriverException
     */
    public static WebDriver startDriver() throws InitDriverException {

        if (driver == null) {
            throw new InitDriverException("Драйвер не инициализирован. Инициализируйте драйвер командой \"initDriver\".");
        }
        return driver;

    }

    /**
     * Метод для завершения работы драйвера
     */
    public static void shutDown() {

        driver.quit();
        driver = null;

    }

}
