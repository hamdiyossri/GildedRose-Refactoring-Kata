package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.utils.TestUtils.FOO_ITEM;
import static com.gildedrose.utils.TestUtils.NORMAL_ITEM;
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

}
