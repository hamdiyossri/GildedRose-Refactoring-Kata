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
            ItemEnum itemEnum = ItemEnum.fromString(item.name);

            if (itemEnum.isLegendary()) {
                return;
            }

            item.sellIn--;

            if (itemEnum == ItemEnum.AGED_BRIE) {
                if (item.sellIn < 0) {
                    if (item.quality < itemEnum.getMaxQuality() - 1) {
                        item.quality += 2;
                    } else {
                        item.quality = itemEnum.getMaxQuality();
                    }
                } else {
                    if (item.quality < itemEnum.getMaxQuality()) {
                        item.quality++;
                    }
                }
            } else if (itemEnum == ItemEnum.BACKSTAGE) {
                if (item.sellIn < 0) {
                    item.quality = 0;
                } else {
                    if (item.quality < itemEnum.getMaxQuality()) {
                        item.quality++;
                    }
                    if (item.sellIn < 11 && item.quality < itemEnum.getMaxQuality()) {
                        item.quality++;
                    }
                    if (item.sellIn < 6 && item.quality < itemEnum.getMaxQuality()) {
                        item.quality++;
                    }
                }
            } else {
                if (item.quality > itemEnum.getMinQuality()) {
                    item.quality--;
                }
                if (item.sellIn < 0 && item.quality > itemEnum.getMinQuality()) {
                    item.quality--;
                }
            }

            item.quality = Math.max(itemEnum.getMinQuality(), Math.min(itemEnum.getMaxQuality(), item.quality));
        });
    }

}
