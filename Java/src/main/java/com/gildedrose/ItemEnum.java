package com.gildedrose;

import java.util.Arrays;

/**
 * Enum representing different types of items with associated properties.
 * <p>
 * Each item has a name, a minimum and maximum quality value, a value, and a flag indicating if it's legendary.
 * </p>
 */
public enum ItemEnum {

    /**
     * Represents a normal item with a null name and quality values ranging from 0 to 50.
     * Its value is -1, and it is not a legendary item.
     */
    NORMAL("Normal", 0, 50, -1, false),

    /**
     * Represents the "Aged Brie" item with quality values ranging from 0 to 50 and a value of 1.
     * Not a legendary item.
     */
    AGED_BRIE("Aged Brie", 0, 50, 1, false),

    /**
     * Represents the "Sulfuras, Hand of Ragnaros" item with fixed quality value of 80 and value of 0.
     * This is a legendary item, meaning it is not subject to normal quality constraints.
     */
    SULFURAS("Sulfuras, Hand of Ragnaros", 80, 80, 0, true),

    /**
     * Represents the "Backstage passes to a TAFKAL80ETC concert" item with quality values ranging from 0 to 50
     * and a value of 1. Not a legendary item.
     */
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert", 0, 50, 1, false),

    /**
     * Represents the "Conjured" item with quality values ranging from 0 to 50
     * and a value of 1. Not a legendary item.
     */
    CONJURED("Conjured", 0, 50, -2, false);


    private final String name;
    private final int minQuality;
    private final int maxQuality;
    private final int value;
    private final boolean isLegendary;

    /**
     * Constructor for creating an instance of the enum with specified properties.
     *
     * @param name The name of the item.
     * @param minQuality The minimum quality value for the item.
     * @param maxQuality The maximum quality value for the item.
     * @param value The value associated with the item.
     * @param isLegendary A flag indicating if the item is legendary.
     */
    private ItemEnum(String name, int minQuality, int maxQuality, int value, boolean isLegendary) {
        this.name = name;
        this.minQuality = minQuality;
        this.maxQuality = maxQuality;
        this.value = value;
        this.isLegendary = isLegendary;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the {@code ItemEnum} corresponding to the specified name, or {@code AUTRE} if no match is found.
     *
     * @param name The name of the item to find.
     * @return The corresponding {@code ItemEnum} or {@code AUTRE} if not found.
     */
    public static ItemEnum fromString(String name) {
        return name != null ? Arrays.stream(values()).filter(element ->
                name.equalsIgnoreCase(element.getName()))
            .findFirst()
            .orElse(NORMAL)
            : NORMAL;
    }

    /**
     * Gets the minimum quality value of the item.
     *
     * @return The minimum quality value of the item.
     */
    public int getMinQuality() {
        return minQuality;
    }

    /**
     * Gets the maximum quality value of the item.
     *
     * @return The maximum quality value of the item.
     */
    public int getMaxQuality() {
        return maxQuality;
    }

    /**
     * Gets the value associated with the item.
     *
     * @return The value of the item.
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the item is legendary.
     *
     * @return {@code true} if the item is legendary, {@code false} otherwise.
     */
    public boolean isLegendary() {
        return isLegendary;
    }
}
