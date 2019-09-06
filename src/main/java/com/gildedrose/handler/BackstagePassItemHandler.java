package com.gildedrose.handler;

import com.gildedrose.Item;

public class BackstagePassItemHandler extends AbstractItemHandler {

	@Override
	public void updateItem(Item item) {
		decreaseSellIn(item);

		if (item.sellIn < 0) {
			item.quality = 0;
		} else if (item.sellIn < 5) {
			increaseQualityIfUnderLimit(item, 3);
		} else if (item.sellIn < 10) {
			increaseQualityIfUnderLimit(item, 2);
		} else {
			increaseQualityIfUnderLimit(item, 1);
		}
	}

}
