package ru.appline.ibs.homework.framework.utils;

/**
 * Класс для формирования карточек продукта
 */
public class ProductCard {

    /**
     * @see ProductCard#name - Название товара
     * @see ProductCard#price - Цена товара
     * @see ProductCard#priceWithGuarantee - Цена товара с учетом дополнительной гарантии
     * @see ProductCard#guarantee - Гарантия производителя
     * @see ProductCard#additionalGuarantee - Дополнительная гарантия
     * @see ProductCard#countryBuilder - Страна-производитель
     * @see ProductCard#rate - Средняя оценка товара
     */
    private String name;
    private long price;
    private long priceWithGuarantee;
    private short guarantee;
    private short additionalGuarantee;
    private String countryBuilder;
    private double rate;

    /**
     * Конструктор для всех товаров
     * @param name - Название товара
     * @param price - Цена товара
     * @param rate - Средняя оценка
     * @param countryBuilder - Страна-производитель
     */
    public ProductCard(String name, long price, double rate, String countryBuilder) {
        this.setName(name);
        this.setPrice(price);
        this.setRate(rate);
        this.setCountryBuilder(countryBuilder);
    }

    /**
     * Конструктор для товаров с дополнительной гарантией
     * @param name - Название товара
     * @param price - Цена товара
     * @param priceWithGuarantee - Цена товара с учетом дополнительной гарантии
     * @param additionalGuarantee - Срок дополнительной гарантии
     * @param rate - Средняя оценка товара
     * @param guarantee - Гарантия производителя
     * @param countryBuilder - Страна-производитель
     */
    public ProductCard(String name, long price, long priceWithGuarantee, short additionalGuarantee, double rate, short guarantee, String countryBuilder) {
        this(name, price, rate, countryBuilder);
        this.setPriceWithGuarantee(priceWithGuarantee);
        this.setAdditionalGuarantee(additionalGuarantee);
        this.setGuarantee(guarantee);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAdditionalGuarantee() {
        return additionalGuarantee;
    }

    public void setAdditionalGuarantee(short additionalGuarantee) {
        this.additionalGuarantee = additionalGuarantee;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public short getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(short guarantee) {
        this.guarantee = guarantee;
    }

    public String getCountryBuilder() {
        return countryBuilder;
    }

    public void setCountryBuilder(String countryBuilder) {
        this.countryBuilder = countryBuilder;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getPriceWithGuarantee() {
        return priceWithGuarantee;
    }

    public void setPriceWithGuarantee(long priceWithGuarantee) {
        this.priceWithGuarantee = priceWithGuarantee;
    }

    /**
     * Выводит на экран информацию о товаре
     * @return String productInfo
     */
    public String getProductInfo() {
        if (this.getPriceWithGuarantee() <= 0 && this.getGuarantee() <= 0) {
            return "Название продукта: " + this.getName() + "/n" +
                    "Цена: " + this.getPrice() + "/n" +
                    "Оценка: " + this.getRate() + " из 5/n" +
                    "Страна-производитель: " + this.getCountryBuilder() + "/n";
        } else {
            return "Название продукта: " + this.getName() + "/n" +
                    "Цена: " + this.getPrice() + "/n" +
                    "Цена с гарантией: " + this.getPriceWithGuarantee() + "/n" +
                    "Гарантия производителя: " + this.getGuarantee() + ((this.getGuarantee() == 12) ? " месяцев" : " месяца") +
                    "Дополнительная гарантия: " + this.getAdditionalGuarantee() + ((this.getAdditionalGuarantee() == 1) ? " год" : " года" ) +
                    "Оценка: " + this.getRate() + " из 5/n" +
                    "Страна-производитель: " + this.getCountryBuilder() + "/n";
        }
    }
}
