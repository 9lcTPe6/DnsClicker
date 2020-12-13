package ru.appline.ibs.homework.framework.exceptions;

public class InitDriverException extends Exception {

    /**
     *  Это класс Exception, созданный для проверки во время инициализации драйвера
     */
    public InitDriverException (String message) {
        super(message);
    }

}
