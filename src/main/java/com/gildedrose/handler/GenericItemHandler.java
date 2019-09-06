package com.gildedrose.handler;

import com.gildedrose.Item;

public class GenericItemHandler extends AbstractItemHandler{

	@Override
	public void updateItem(Item item) {
		decreaseSellIn(item);

		if (item.sellIn < 0) {
			decreaseQualityIfAboveZero(item, 2);
		} else {
			decreaseQualityIfAboveZero(item, 1);
		}
	}

}
