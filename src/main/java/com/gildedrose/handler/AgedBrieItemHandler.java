package com.gildedrose.handler;

import com.gildedrose.Item;

public class AgedBrieItemHandler extends AbstractItemHandler{

	@Override
	public void updateItem(Item item) {
		increaseQualityIfUnderLimit(item);
		decreaseSellIn(item);
		if (item.sellIn < 0) {
			increaseQualityIfUnderLimit(item);
		}
	}
}
