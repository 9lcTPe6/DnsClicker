package ru.appline.ibs.homework.framework.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Менеджер, отвечающий за обработку файла .properties
 */
public class PropertyManager {

    private final Properties PROPERTIES = new Properties();

    private static PropertyManager instance = null;

    /**
     * Файл по умолчанию: app.properties
     * Возможное значение: environmental.properties
     */
    private PropertyManager() {

        try {
            PROPERTIES.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("environmental", "app") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static PropertyManager getPropertyManager() {

        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;

    }

    public String getProperty(String key, String defaultValue) {

        return PROPERTIES.getProperty(key, defaultValue);

    }

    public String getProperty(String key) {

        return PROPERTIES.getProperty(key);

    }

}
