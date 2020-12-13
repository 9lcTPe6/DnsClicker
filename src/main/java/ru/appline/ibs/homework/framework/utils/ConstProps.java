package ru.appline.ibs.homework.framework.utils;

/**
 * Enum (латиница) констант из файла .properties
 */
public enum ConstProps {

    TEST_URL ("site.url"),
    BROWSER_TYPE ("type.browser"),
    CHROME_DRIVER ("path.chrome.driver"),
    FIREFOX_DRIVER ("path.gecko.driver"),
    IMPLICITLY_WAIT ("implicitly.wait"),
    PAGELOAD_WAIT ("page.load.timeout"),
    SCRIPT_WAIT ("set.script.timeout");

    private final String CONST_PROP;

    ConstProps(String CONST_PROP) {
        this.CONST_PROP = CONST_PROP;
    }

    public String getConstantProp() {
        return this.CONST_PROP;
    }

}
