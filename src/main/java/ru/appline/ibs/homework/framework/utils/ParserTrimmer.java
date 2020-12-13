package ru.appline.ibs.homework.framework.utils;

/**
 * Утилита для парсинга цен. Удаляет пробелы и знак ₽, €, $ и прочие, не являющиеся цифрами. В том числе точки,
 * поэтому не подходит для обработки дробных чисел
 */
public class ParserTrimmer {

    /**
     * Метод для обрезки для парсинга
     * @param stringForTrim - Строка, которую надо отформатировать, удалив символы
     * @return String parsedString
     */
    public static String parserTrimmer(String stringForTrim) {
        char[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        char[] stringForParse = stringForTrim.toCharArray();
        String parsedString = String.valueOf(stringForParse[0]);

        for (int i = 1; i < stringForParse.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[j] == stringForParse[i]) {
                    parsedString += stringForParse[i];
                }
            }
        }

        return parsedString;
    }

}
