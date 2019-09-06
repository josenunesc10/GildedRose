package com.gildedrose.handler;

import com.gildedrose.Item;

public class ConjuredItemHandler extends AbstractItemHandler{

	@Override
	public void updateItem(Item item) {
		decreaseSellIn(item);

		if (item.sellIn < 0) {
			decreaseQualityIfAboveZero(item, 4);
		} else {
			decreaseQualityIfAboveZero(item, 2);
		}
	}

}
