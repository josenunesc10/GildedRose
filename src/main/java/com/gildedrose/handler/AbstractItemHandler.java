package com.gildedrose.handler;

import com.gildedrose.Item;

public abstract class AbstractItemHandler  implements ItemHandler {
	protected int MAX_QUALITY = 50;
	protected int MIN_QUALITY = 0;
	
	protected void decreaseQualityIfAboveZero(Item item, int amount) {
		item.quality = item.quality - amount;
		if (item.quality < MIN_QUALITY) {
			item.quality = MIN_QUALITY;
		}
	}

	protected void increaseQualityIfUnderLimit(Item item, int amount) {
		item.quality = item.quality + amount;
		if (item.quality > MAX_QUALITY) {
			item.quality = MAX_QUALITY;
		}
	}
	
	protected void decreaseSellIn(Item item) {
		item.sellIn = item.sellIn - 1;
	}
	
	public abstract void updateItem(Item item);
}
