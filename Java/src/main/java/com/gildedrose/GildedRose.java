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
                    update(item, 2, 0, itemEnum.getMaxQuality());
                } else {
                    update(item, 1, 0, itemEnum.getMaxQuality());
                }
            }
            else if (itemEnum == ItemEnum.BACKSTAGE) {
                if (item.sellIn < 0) {
                    item.quality = 0;
                } else {
                    update(item, 1, 0, itemEnum.getMaxQuality());

                    if (item.sellIn < 11) {
                        update(item, 1, 0, itemEnum.getMaxQuality());
                    }

                    if (item.sellIn < 6) {
                        update(item, 1, 0, itemEnum.getMaxQuality());
                    }
                }
            }
            else if (itemEnum == ItemEnum.CONJURED) {
                if (item.sellIn < 0) {
                    update(item, -4, itemEnum.getMinQuality(), itemEnum.getMaxQuality());
                } else {
                    update(item, -2, itemEnum.getMinQuality(), itemEnum.getMaxQuality());
                }
            }
            else {
                if (item.quality > itemEnum.getMinQuality()) {
                    update(item, -1, itemEnum.getMinQuality(), itemEnum.getMaxQuality());
                }

                if (item.sellIn < 0 && item.quality > itemEnum.getMinQuality()) {
                    update(item, -1, itemEnum.getMinQuality(), itemEnum.getMaxQuality());
                }
            }

            item.quality = Math.max(itemEnum.getMinQuality(), Math.min(itemEnum.getMaxQuality(), item.quality));
        });
    }


    private void update(Item item, int value, int minQuality, int maxQuality) {
        if (item.quality + value < minQuality) {
            item.quality = minQuality;
        } else if (item.quality + value > maxQuality) {
            item.quality = maxQuality ;
        } else if (item.quality > minQuality && item.quality < maxQuality) {
            item.quality = item.quality + value;
        }
    }
}
