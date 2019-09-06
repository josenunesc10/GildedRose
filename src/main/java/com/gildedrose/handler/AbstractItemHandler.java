package com.gildedrose.handler;

import com.gildedrose.Item;

public abstract class AbstractItemHandler  implements ItemHandler {
	protected int MAX_QUALITY = 50;
	
	protected void decreaseQualityIfAboveZero(Item item) {
		if (item.quality > 0) {
			item.quality = item.quality - 1;
		}
	}

	protected void decreaseSellIn(Item item) {
		item.sellIn = item.sellIn - 1;
	}

	protected void increaseQualityIfUnderLimit(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;
		}
	}
	
	public abstract void updateItem(Item item);
}
