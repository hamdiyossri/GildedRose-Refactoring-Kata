package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return this.items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                return;
            }

            item.sellIn--;

            if (item.name.equals("Aged Brie")) {
                if (item.sellIn < 0) {
                    item.quality = Math.min(50, item.quality + 2);
                } else {
                    item.quality = Math.min(50, item.quality + 1);
                }
            }
            else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.sellIn < 0) {
                    item.quality = 0;
                } else {
                    if (item.quality < 50) {
                        item.quality++;
                    }

                    if (item.sellIn < 11 && item.quality < 50) {
                        item.quality++;
                    }

                    if (item.sellIn < 6 && item.quality < 50) {
                        item.quality++;
                    }
                }
            }
            else {
                if (item.quality > 0) {
                    item.quality--;
                }

                if (item.sellIn < 0 && item.quality > 0) {
                    item.quality--;
                }
            }

            item.quality = Math.max(0, Math.min(50, item.quality));
        });
    }
}
