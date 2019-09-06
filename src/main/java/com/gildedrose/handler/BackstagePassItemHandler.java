package com.gildedrose.handler;

import com.gildedrose.Item;

public class BackstagePassItemHandler extends AbstractItemHandler {

	@Override
	public void updateItem(Item item) {
		increaseQualityIfUnderLimit(item);
		if (item.sellIn < 11) {
			increaseQualityIfUnderLimit(item);
		}

		if (item.sellIn < 6) {
			increaseQualityIfUnderLimit(item);
		}
		decreaseSellIn(item);
		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}

}
