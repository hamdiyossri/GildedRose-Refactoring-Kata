package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item(FOO_ITEM, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(FOO_ITEM, app.items[0].name);
    }

    /**
     * Normal items
     * - The `sellIn` value decreases by 1 at the end of the day.
     * - The `quality` value decreases by 1 at the end of the day.
     */
    @Test
    public void normalItemQualityAndSellInDecrease() {
        Item[] items = new Item[] {new Item(NORMAL_ITEM, 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item =  app.items[0];
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }


    /**
     * Normal items
     * - Once the `sellIn` value is 0 or less, the `quality` value degrades twice as fast.
     * - `sellIn` decreases by 1 at the end of the day.
     */
    @Test
    public void normalItemQualityDegradesTwiceAfterSellInPassed() {

        Item[] items = new Item[] { new Item(NORMAL_ITEM, 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item =  app.items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(18, item.quality);
    }

    /**
     * Normal items
     * - The `quality` value never goes below 0, even when it degrades twice as fast.
     */
    @Test
    public void normalItemQualityNeverNegative() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM, 0, 1)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    /**
     * "Aged Brie" items
     * - The `sellIn` value decreases by 1 at the end of the day.
     * - The `quality` value increases by 1 at the end of the day.
     */
    @Test
    public void agedBrieQualityIncreasesBeforeSellIn() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 30)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(9, item.sellIn);
        assertEquals(31, item.quality);
    }

    /**
     * "Aged Brie" items
     * - The `quality` value increases by 2 when the `sellIn` value is 0 or less.
     */
    @Test
    public void agedBrieQualityIncreasesFasterAfterSellIn() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 30)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(32, item.quality);
    }

    /**
     * "Aged Brie" items
     * - The `quality` value never exceeds 50, even as it improves over time.
     */
    @Test
    public void agedBrieQualityNeverExceeds50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(4, item.sellIn);
        assertEquals(50, item.quality);
    }

    /**
     * "Sulfuras" items
     * - The `quality` value remains at 75.
     */
    @Test
    public void sulfurasQualityIsAlwaysSame() {
        Item[] items = new Item[] { new Item(SULFURAS, 10, 75)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(10, item.sellIn);
        assertEquals(75, item.quality);
    }

    /**
     * "Sulfuras" items
     * - The `sellIn` and `quality` values remain unchanged even if the sell-in date has passed.
     */
    @Test
    public void sulfurasUnchangedAfterSellInPassed() {
        Item[] items = new Item[] {new Item(SULFURAS, -1, 75)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Item item = app.items[0];
        assertEquals(-1, item.sellIn);
        assertEquals(75, item.quality);
    }
}
